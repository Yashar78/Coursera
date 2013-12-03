myAlpha = [ 0.01, 0.003,0.001];
%myIters = [100,200,400,800,1600,3200];

myIters = [100,200];%,400,800,1600,3200];

d1 = size(myAlpha,2);

d2 = size(myIters,2);
for a=1:size(myAlpha,2)
	for i=1:size(myIters,2)
		alpha = myAlpha(1,a);
		num_iters = myIters(1,i);

		% Init Theta and Run Gradient Descent 
		theta = zeros(3, 1);
		[theta, J_history] = gradientDescentMulti(X, y, theta, alpha, num_iters);
	        J_history
		%hold on;
		subplot(a,i,a.*i);
		plot(1:numel(J_history), J_history, '-b', 'LineWidth', 2);
		xlabel('Number of iterations');
	%title('alpha = ')
	end

end
 

