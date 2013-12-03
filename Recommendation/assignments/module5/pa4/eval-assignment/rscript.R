# Create a chart comparing the algorithms

library("ggplot2")
library("grid")

all.data <- read.csv("~/Coursera/Recommendation/assignments/module5/pa4/eval-assignment/target/analysis/eval-results.csv")
df <-  all.data
all.agg <- aggregate(cbind(Coverage,  RMSE.ByRating,	RMSE.ByUser,	nDCG,	TopN.nDCG,	TagEntropy.10)
                     ~ Algorithm+NNbrs,
                     data=all.data, mean)



#Minimum number to beat MeanPers, question 2 
df21 <- df[df$Algorithm %in% c("PersMean"),]
q21 <- aggregate(cbind(RMSE.ByRating,  RMSE.ByUser, nDCG)  ~ Algorithm, data=df21, mean)
df22 <- df[df$Algorithm %in% c("LuceneNorm"),]
q22 <- aggregate(cbind(RMSE.ByRating,  RMSE.ByUser, nDCG)  ~ Algorithm+NNbrs, data=df22, mean)
min(q22[q22$RMSE.ByUser < q21$RMSE.ByUser[1],]$NNbrs)


#question 3 
min(q22[q22$nDCG > q21$nDCG[1],]$NNbrs)


#question 4
q4 <- aggregate(cbind(RMSE.ByRating,  RMSE.ByUser,	nDCG)
                     ~ Algorithm+NNbrs,
                     data=all.data, mean)

q4[q4$nDCG==max(q4$nDCG),]

q4[q4$RMSE.ByUser==min(q4$RMSE.ByUser),]

#question 5 
all.agg[all.agg$TagEntropy.10==max(all.agg$TagEntropy.10),]

#question 6 
q6 <- all.agg[,c("Algorithm","NNbrs", "TagEntropy.10" )]

ggplot(q6, aes(x=NNbrs, y=TagEntropy.10, colour=Algorithm)) +geom_line()


#question 7 
q7 <- all.agg[,c("nDCG","TopN.nDCG")]

qplot(q7)
sum(q7$nDCG > q7$TopN.nDCG)

#question 8 
q8 <- aggregate(cbind(	nDCG, BuildTime ,TestTime)
                     ~ Algorithm,
                     data=all.data, mean)

#question 9 
q9 <- aggregate(cbind(Coverage,  RMSE.ByRating,  RMSE.ByUser,	nDCG,	TopN.nDCG)
                ~ Algorithm,
                data=all.data, mean)

q9 <- q9[q9$Algorithm %in% c("UserUser", "UserUserNorm", "UserUserNormCosine"),]

#question 10 
q10 <- aggregate(cbind(TagEntropy.10)
                 ~ Algorithm,
                 data=all.data, mean)

ggplot(q10, aes(x=Algorithm, y=TagEntropy.10)) +geom_point()

#q12 
q12 <- aggregate(cbind(TopN.nDCG)
                ~ Algorithm,
                data=all.data, mean)

q12[q12$Algorithm=="Popular",]