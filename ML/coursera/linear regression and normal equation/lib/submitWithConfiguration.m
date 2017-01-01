function submitWithConfiguration(conf)
  addpath('./lib/jsonlab');

  parts = parts(conf);

  tokenFile = 'token.mat';
  if exist(tokenFile, 'file')
    load(tokenFile);
    [email token] = promptToken(email, token);
  else
    [email token] = promptToken('', '');
  end

  if isempty(token)
    fprintf('!! Submission Cancelled\n');
    return
  end

  fprintf('== Submitting solutions | %s...\n', conf.itemName);
  try
    response = submitParts(conf, email, token, parts);
  catch
    fprintf(
      '!! Submission failed: unexpected error; please try again later\n');
    return
  end

  if isfield(response, 'errorMessage')
    fprintf('!! Submission failed: %s\n', response.errorMessage);
  else
    fprintf('== Submission succeeded\n');
    showFeedback(parts, response);
    save(tokenFile, 'email', 'token');
  end
end

function [email token] = promptToken(email, existingToken)
  if (~isempty(email) && ~isempty(existingToken))
    disp(['You are submitting as ' email '.']);
    reenter = input( ...
      'Is this you? (y/n - type n to reenter token): ', 's');

    if (isempty(reenter) || reenter(1) == 'Y' || reenter(1) == 'y')
      token = existingToken;
      return;
    end
  end
  email = input('Login (email address): ', 's');
  token = input('Token: ', 's');
end

function isValid = isValidPartOptionIndex(partOptions, i)
  isValid = (~isempty(i)) && (1 <= i) && (i <= numel(partOptions));
end

function response = submitParts(conf, email, token, parts)
  body = makePostBody(conf, email, token, parts);
  submissionUrl = submissionUrl();
  params = {'jsonBody', body};
  responseBody = urlread(submissionUrl, 'post', params);
  response = loadjson(responseBody);
end

function body = makePostBody(conf, email, token, parts)
  bodyStruct.assignmentSlug = conf.assignmentSlug;
  bodyStruct.submitterEmail = email;
  bodyStruct.secret = token;
  bodyStruct.parts = makePartsStruct(conf, parts);

  opt.Compact = 1;
  body = savejson('', bodyStruct, opt);
end

function partsStruct = makePartsStruct(conf, parts)
  for part = parts
    partId = part{:}.id;
    fieldName = makeValidFieldName(partId);
    outputStruct.output = conf.output(partId);
    partsStruct.(fieldName) = outputStruct;
  end
end

function [parts] = parts(conf)
  parts = {};
  for partArray = conf.partArrays
    part.id = partArray{:}{1};
    part.sourceFiles = partArray{:}{2};
    part.name = partArray{:}{3};
    parts{end + 1} = part;
  end
end

function showFeedback(parts, response)
  fprintf('== \n');
  fprintf('== %43s | %8s | %-s\n', 'Part Name', 'Score', 'Feedback');
  fprintf('== %43s | %8s | %-s\n', '---------', '-----', '--------');
  for part = parts
    score = '';
    partFeedback = '';
    partFeedback = response.partFeedbacks.(makeValidFieldName(part{:}.id));
    partEvaluation = response.partEvaluations.(makeValidFieldName(part{:}.id));
    score = sprintf('%d / %d', partEvaluation.score, partEvaluation.maxScore);
    fprintf('== %43s | %8s | %-s\n', part{:}.name, score, partFeedback);
  end
  fprintf('== \n');
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Service configuration
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function submissionUrl = submissionUrl()
  submissionUrl = 'https://www.coursera.org/api/onDemandProgrammingImmediateFormSubmissions.v1';
end
