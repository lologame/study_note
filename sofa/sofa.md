### sofa启动流程

1. 获取sofa-config，存放于类`TestUtils`的`private static Properties classpathSofaProperties`中。

2. 执行`iniSystemProperties()`方法。

   * 设置一些第三方实现可能加载到的系统属性
   * 将ats-config.properties中的属性加载到`TestUtils`的`atsProperties`中。

3. 执行`setUpCloudEngineIfNecessary()`方法

   * 设置中间件端口(tr,ws,drm_tr,drm_rmi,schedular)
   * 设置ce路径，并存放到系统属性中

4. 执行`createAce()` 打包成ace文件，存放在java.io.tmpdir路径下

   ace文件结构：

   ​	—— META-INF ——  MAINFEST.MF

   ​	—— WEB-INF —— web.xml

   ​	—— config —— sofa-config.properties	sofa-log4j.properties

5. 执行`createFramework(String appType)` 

   1. 生成sofa-runtime-test插件
   2. 执行EquinoxLaucher的start方法
      1. 初始化和设置一些Equinox相关的属性

         ```java
         FrameworkProperties.initializeProperties();
         this.setEquinoxProperties(this.configuration);
         ```

      2. 创建一个Framework并且启动这个Framework，该实例代表OSGI框架本身。

         ```java
         //在构造函数中初始化了系统bundle，服务注册表，bundle仓库
         current = new org.eclipse.osgi.framework.internal.core.Framework(new BaseAdaptor(new String[0]));
         ```

      3. 执行current.launch();
        1. 创建一个表示Equinox处于运行状态的线程
        2. 执行 `this.systemBundle.resume();` 执行系统bundle的`BundleActivator.start()`,在这个过程中会注册一系列的服务，包括startLevelManager,packageAdmin,securityAdmin等等. 启动系统bundle后，改变startLevel,创建了一条名为start level envent dispatcher的线程，通过调用StartLevelManager.incFWSL每次将startlevel增加1，然后执行符合条件的bundle的resume方法。`this.resumeBundles(launchBundles, incToSL);`
        3. 安装并start一系列用户bundle
        4. deployAce()  deploy是基于Pipeline的，pipeline里面有个stageList,根据stageList按顺序执行
            整个过程一个有18个stage，执行每个stage的process方法
            * EclipseAcePluginInstallStage
            * SystemPorpertiesLoadingStage    和antx相关的全局属性
            * WebIdentifyStage
            * EclipseModelCreatingStage    
              1. 创建了一个ApplicationRuntimeModel
              2. 获取所有module
              3. 对于jar，封装为JarDeploymentDescriptor,添加至ApplicationRuntimeModel的deploys
              4. 对于业务module，封装为FileDeploymentDescriptor，添加至ApplicationRuntimeModel的deploys
            * WebContextCreatingStage 重构了下web.xml
            * AceBundleInstallStage 将ace bundle安装到CE中 并且在installArtifact中的bundle设置为这个bundle
            * AppStartingStage start这个bundle
            * AppConfigurationProcessStage
            * AceDeploymentCreatingStage
              1. 获取bundle的classLoader KernelAceClassLoader
              2. 为fabricsrv.ace_1.0.0.jar创建了一个VirtualDeploymentDescriptor
              3. 将该DeploymentDescriptor添加到了ApplicationRuntimeModel的deploys
            * SofaRuntimeCreateStage
              运行SofaFrameworkImpl.start(), 为SofaFrameworkImpl创建一堆Listener
            
          

         ​