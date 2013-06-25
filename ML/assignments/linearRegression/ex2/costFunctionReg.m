function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta


N = size(X,2);
tempTheta = theta(2:N,:);
J = (-1./m).*(log(sigmoid((X*theta)'))*y+log(1.-sigmoid((X*theta)'))*(1.-y))+(lambda./(2.*m).*(tempTheta'*tempTheta));

%for i =1:size(theta,1)
%	if (i==1)
%		grad(i) = (1/m)*(sigmoid((X*theta)')-y')*X(:,i);
%	else
%		grad(i) = (1/m)*(sigmoid((X*theta)')-y')*X(:,i).+(lambda.*theta(i))./m;
%	end
%end
grad = (1./m).*(sigmoid((X*theta)')-y')*X;
temp = theta;
temp = (lambda./m).*theta';
%fprintf('----------- > size of temp (%i, %i)\n', size(temp,1),size(temp,2) );
temp(1) = 0;
%fprintf('----------- > size of theta (%i, %i)\n', size(theta,1),size(theta,2) );
grad = grad + temp;

% =============================================================

end
