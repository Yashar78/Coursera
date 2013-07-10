function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 0.3;
sigma = 0.1;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%
c = 0.01;
c_list = [];
while (c <= 30)
   c_list = [c_list c];
   c = c* 3;
end

s = 0.01;
s_list = [];
while (s < 1)
   s_list = [s_list s];
   s = s* 2;
end

c_list = [0.01 0.03 0.1 0.3 1 3 10 30];

s_list = [0.01 0.03 0.1 0.3 1 3 10 30];

c_list_size = size(c_list,2);
s_list_size = size(s_list,2); 

best_error= 1000000.0;
best_c = 0;
best_sigma = 0;

for i=1:c_list_size
  for j=1:s_list_size
     C = c_list(i);
     sigma = s_list(j); 
     model = svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
     predictions = svmPredict(model, Xval);
     error =  mean(double(predictions ~= yval));
    
     if (error < best_error)
     	best_c = C;
        best_sigma = sigma;
        best_error = error; 
     end
     fprintf('C = %f \t\t sigma=%f \t\t best (C, sigma)= (%f,%f)', C, sigma,best_c,best_sigma);


     
  end
end

C= best_c;
sigma = best_sigma;




% =========================================================================

end
