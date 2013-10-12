# recommenation assignemnt 2 
# read the data
setwd("/Users/rahimdelaviz/Coursera/Recommendation/assignments/module2/assign2")
userData <- read.csv("recsys_data_WA 1 Rating Matrix.csv", header=TRUE)

#find top 5 movies by mean

meanValues <- sapply(userData[, -1], FUN=mean, na.rm= TRUE)
meanValues <- sort(meanValues, decreasing=TRUE)[c(1:5)]
x <- names(meanValues)#c("X565..X223")
m <- regexpr("[0-9]+",x, perl=TRUE)
names(meanValues) <- regmatches(x,m)
write.table(names(meanValues), file="meanValues.csv", col.names=F,
          row.names=FALSE, quote=FALSE )

countN <- function ( v ) {
  length ( v ) - sum ( is.na ( v ) )
} 

countValues <- sapply(userData[, -1], FUN=countN )
countValues <- sort(countValues, decreasing=TRUE)[c(1:5)]
x <- names(countValues)#c("X565..X223")
m <- regexpr("[0-9]+",x, perl=TRUE)
names(countValues) <- regmatches(x,m)
write.table(names(countValues), file="countValues.csv", col.names=F,
          row.names=FALSE, quote=FALSE )


countHighRatesPercent <- function (v, minRate){
  
  nonNa <- length(v) - sum(is.na(v))
  
  higherThanMin <- sum(v>=minRate, na.rm=T)
  
  return (100*higherThanMin / nonNa)
  
}

countHighRates <- sapply(userData[, -1], FUN=countHighRatesPercent , minRate=4 )
countHighRates <- sort(countHighRates, decreasing=TRUE)[c(1:5)]
x <- names(countHighRates)#c("X565..X223")
m <- regexpr("[0-9]+",x, perl=TRUE)
names(countHighRates) <- regmatches(x,m)
write.table(names(countHighRates), file="countHighRates.csv", col.names=F,
          row.names=FALSE, quote=FALSE )



starWarsAss <- function (v, starWars ){
  return (100*sum(!is.na(v) & !is.na(starWars))/sum(!is.na(starWars)))
  
}

withStarWars <- sapply(userData[, -1], FUN=starWarsAss, starWars=userData[,2])
withStarWars <- sort(withStarWars, decreasing=TRUE)[2:6]
x <- names(withStarWars)#c("X565..X223")
m <- regexpr("[0-9]+",x, perl=TRUE)
names(withStarWars) <- regmatches(x,m)
write.table(names(withStarWars), file="countHighEithStars.csv", col.names=FALSE,
          row.names=FALSE, quote=FALSE )

