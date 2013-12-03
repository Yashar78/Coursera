# Create a chart comparing the algorithms

library("ggplot2")
library("grid")

multiplot <- function(..., plotlist=NULL, file, cols=1, layout=NULL) {
  require(grid)
  
  # Make a list from the ... arguments and plotlist
  plots <- c(list(...), plotlist)
  
  numPlots = length(plots)
  
  # If layout is NULL, then use 'cols' to determine layout
  if (is.null(layout)) {
    # Make the panel
    # ncol: Number of columns of plots
    # nrow: Number of rows needed, calculated from # of cols
    layout <- matrix(seq(1, cols * ceiling(numPlots/cols)),
                     ncol = cols, nrow = ceiling(numPlots/cols))
  }
  
  if (numPlots==1) {
    print(plots[[1]])
    
  } else {
    # Set up the page
    grid.newpage()
    pushViewport(viewport(layout = grid.layout(nrow(layout), ncol(layout))))
    
    # Make each plot, in the correct location
    for (i in 1:numPlots) {
      # Get the i,j matrix positions of the regions that contain this subplot
      matchidx <- as.data.frame(which(layout == i, arr.ind = TRUE))
      
      print(plots[[i]], vp = viewport(layout.pos.row = matchidx$row,
                                      layout.pos.col = matchidx$col))
    }
  }
}



all.data <- read.csv("~/Coursera/Recommendation/assignments/module7/pa6/svd-assignment/target/analysis/eval-results.csv")
df <-  all.data


#quizz question 1
all.agg <- aggregate(cbind(RMSE.ByUser,  nDCG)
                     ~ Algorithm,
                     data=all.data, mean)



#quizz question 2
#df1 <- df[df$Algorithm %in% c("CustomItemItem"),]
q2 <- aggregate(cbind(RMSE.ByRating,  RMSE.ByUser, nDCG, TopN.nDCG)  ~ Algorithm+DataSet, data=all.data, mean)


#quizz question 2
#df1 <- df[df$Algorithm %in% c("CustomItemItem"),]
q3 <- aggregate(cbind(RMSE.ByRating,  RMSE.ByUser, nDCG, TopN.nDCG)  ~ Algorithm+FeatureCount, data=all.data, mean)
p1 <- ggplot(q3, aes(x=FeatureCount, y=RMSE.ByUser, colour=Algorithm)) +geom_line()+geom_point()
p2 <- ggplot(q3, aes(x=FeatureCount, y=RMSE.ByRating, colour=Algorithm)) +geom_line()+geom_point()
p3 <- ggplot(q3, aes(x=FeatureCount, y=nDCG, colour=Algorithm)) +geom_line()+geom_point()
p4 <- ggplot(q3, aes(x=FeatureCount, y=TopN.nDCG, colour=Algorithm)) +geom_line()+geom_point()



multiplot(p1, p2,p3,p4, rows=4)
