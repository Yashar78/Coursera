<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1382889406452" ID="ID_1844664503" MODIFIED="1382889417178" TEXT="Octave">
<node CREATED="1382889419581" ID="ID_1116617281" MODIFIED="1382889430298" POSITION="right" TEXT="tutorial from class">
<node CREATED="1382889437731" ID="ID_528285192" MODIFIED="1382889923140" TEXT="Basic ops">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      disp(sprintf('2 descimal: %0.2f',a ))
    </p>
    <p>
      format long&#160;&#160;% long format
    </p>
    <p>
      format short
    </p>
    <p>
      v = 1:0.2:2
    </p>
    <p>
      w = randn(1,3) % from normal distribution
    </p>
    <p>
      
    </p>
    <p>
      help [command]
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1382889925052" ID="ID_1861294838" MODIFIED="1382890889751" TEXT="Moving data around">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      sz = size(A), #rows = size(A,1), #cols=size(A,2)
    </p>
    <p>
      
    </p>
    <p>
      A([1 3],:) % rows 1 and 3
    </p>
    <p>
      A(:,2) = [10;11;12] % change the 2nd column
    </p>
    <p>
      A=[A, [100;101;102]] % appends a columsn
    </p>
    <p>
      A(:) % put A in a column vector
    </p>
    <p>
      
    </p>
    <p>
      C = [A B] % concat A and B along columns
    </p>
    <p>
      A = [A;B] % put on top of each other
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
    <p>
      load(filename) % to load data
    </p>
    <p>
      who % to list vars
    </p>
    <p>
      whos % more detail info about the vars in the current space.
    </p>
    <p>
      clear [var]
    </p>
    <p>
      save filename.dat v; %saves the v to the file in binary format , with load filename loads back
    </p>
    <p>
      save filename filename.txt v --ascii % saves in ascii format
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1382890892076" ID="ID_605333706" MODIFIED="1382891602364" TEXT="Computing">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      .[ops] % element wise ops like 1 ./ A
    </p>
    <p>
      log(v), exp, abs,&#160;&#160;% element wise log
    </p>
    <p>
      A'&#160;&#160;% transpose
    </p>
    <p>
      [val ,ind] = max(A) % columns wise max
    </p>
    <p>
      A &lt; 3 % elemetwise comp
    </p>
    <p>
      find (a &lt; 3) % which element are true
    </p>
    <p>
      
    </p>
    <p>
      sum(A)
    </p>
    <p>
      prod(A)
    </p>
    <p>
      ceil(A)
    </p>
    <p>
      max(A,[],1) % column-wise max
    </p>
    <p>
      max(A,[],2) % row-wise max
    </p>
    <p>
      
    </p>
    <p>
      max(max(A)) % max entire matrix
    </p>
    <p>
      sum(A,1) % per column sum
    </p>
    <p>
      sum(A,2) % per row sum
    </p>
    <p>
      flipud(A) % flips the matrix
    </p>
    <p>
      pinv(A) % inverts A
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1382892223156" ID="ID_1767850634" MODIFIED="1382894013542" TEXT="plotting">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      plot(x,y) % x and y are equal length vectors
    </p>
    <p>
      hold on % new figure on top of old one
    </p>
    <p>
      xlabel(''),&#160;&#160;ylabel('')
    </p>
    <p>
      legend('sin', 'cos')
    </p>
    <p>
      print -dpng 'filename.png'
    </p>
    <p>
      close % remove figure
    </p>
    <p>
      figure(1); plot(x,y);
    </p>
    <p>
      sunplot(1,2,1) divide 1 by 2 grid and access the first part
    </p>
    <p>
      plot(t,y1)
    </p>
    <p>
      sunplot(1,2,2) ;
    </p>
    <p>
      plot(t,y2)
    </p>
    <p>
      axis([range]) % axis range
    </p>
    <p>
      clf; % clear figure
    </p>
    <p>
      imagesc(A) % plots a matric
    </p>
    <p>
      imagesc(A), colorbar, colormap gray;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1382894015045" ID="ID_226530788" MODIFIED="1382896196281" TEXT="loops">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      for i=m:n,
    </p>
    <p>
      ....
    </p>
    <p>
      end;
    </p>
    <p>
      
    </p>
    <p>
      indices=1:10;
    </p>
    <p>
      for i=indices,
    </p>
    <p>
      
    </p>
    <p>
      end;
    </p>
    <p>
      addpath(path) % add a path to the search path
    </p>
    <p>
      
    </p>
    <p>
      function [y1,y1] = sss(x) % returns two values
    </p>
    <p>
      
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
</map>
