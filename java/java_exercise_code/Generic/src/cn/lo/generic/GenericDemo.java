package cn.lo.generic;

public class GenericDemo {

		public static void main(String[] args) {
			
			Manager ceo = new Manager("Jake", 1000,500);
			Manager cfo = new Manager("Cindy",2000,1000);
			
			Pair<Manager> buddies = new Pair<Manager>(ceo, cfo);
			
			printBuddies(buddies);
			
			Manager[] m = {ceo,cfo};
			
			Pair<Employee> res = new Pair<Employee>(null, null);
			
			minmaxBonus(m,res);
			
			System.out.println("min:" + res.getFirst().getName() + ";" + "max:" + res.getSecond().getName());
			
			maxminBouns(m,res);
			
			System.out.println("max:" + res.getFirst().getName() + ";" + "min:" + res.getSecond().getName());
			
			
			
		}
		
		public static void printBuddies(Pair<? extends Employee> p){
			
			Employee first = p.getFirst();
			Employee second = p.getSecond();
			
			System.out.println(first.getName() + " and " + second.getName() + " are " + "buddies!");
		}
		
		
		public static void minmaxBonus(Manager[] a , Pair<? super Manager> result){
			if(a == null || a.length == 0)	return;
			Manager min = a[0];
			Manager max = a[0];
			
			for (int i = 0 ; i < a.length ; i++)
			{
				if(min.getBonus() > a[i].getBonus())	min = a[i];
				if(max.getBonus() < a[i].getBonus())	max = a[i];
			}
			
			result.setFirst(min);
			result.setSecond(max);
		}
		
		public static void maxminBouns(Manager[] a , Pair<? super Manager> result){
			minmaxBonus(a,result);
			PairALg.swap(result);
		}

}
