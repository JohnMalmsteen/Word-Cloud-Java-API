# Word Cloud API

Java Wordie-style word cloud api that uses the Graphics2D library and can take in local file sources as well as web address sources
This Project is for my 4th year Advanced Object Oriented Software Development module (semester 1)

![www.gmit.ie cloud](http://oi66.tinypic.com/erftxw.jpg)

Contents:
---------
1. About
2. Architecture
3. How to Use the API
4. Tools & Environment used

1 - About
---
This API is built to be easy to use, easy to extend and highly flexible. All of the nuts and bolts code that does the work is exposed by interfaces
and so can be chopped and changed at will. Objects can be dynamically bound at run time based on environmental context or user
input.

2. Architecture
---
![architectureuml](http://i.imgur.com/qta0rnn.png)

Working from 'back' to 'front' we have:

#### Parseable.java
This interface contains one method:

```java
WordFrequencyKeyValue[] parse(String source);
```

Which takes the 'source' parameter (whether it be a url or a path to a text file) and returns an array of WordFrequencyKeyValues 
which is a custom class which contains a String, an int for it's frequency of occurrence and an int which will be it's assigned font size.

In this instance the array contains the top 150 most frequent words in the source text.

The WordFrequencyKeyValue class overrides equals and hash to be the hash method of the string since we want these to be unique
and to be hashable for maps/sets etc.

I have also implemented the Comparator interface for this class in WordFrequencyComparator, so that I can take advantage of the Arrays.sort method
which gives a mergesort with guaranteed n*log(n) performance

The implementations of this class are TextFileParserImpl.java and UrlParserImpl.java which read from file and web resource respectively.

#### Filterable.java
This interface contains one method:

```java
boolean checkWord(String word);
```

which takes in a String and checks whether it should be filtered from the output. The idea is to remove uninteresting linking words and the paraphenelia of grammar from the output, currently there is only one implementation of this interface: WordFilterImpl.java which reads from the stopwords.txt file to build a filter. This class is composed with a HashSet and delegates the checkword() method to the hashSets contains() method, although there is a bit of extra code in this method which strips out small numbers.

#### FontSizeSelectionStrategy.java
This interface contains one method:

```java
public WordFrequencyKeyValue[] getFontSizes(WordFrequencyKeyValue[] arrayOfWordKeyValues);
```

Which takes the top 150 words from the Parseable object and assigns font sizes to them.

This is making use of the strategy pattern so the actual strategy that is used will be determined at run time. 

For demonstration I have it set so that if there is less than 150 unique words in the source file it will be using the 
LinearFontSizeStrategy.java implementation otherwise it will use the WeightedFontStrategy.java implementation.

#### Placeable.java
This interface contains two methods:

```java
void placeString(WordFrequencyKeyValue word);
void complete(String outputName);
```

the placeString method takes in a WordFrequencyKeyValue (I supply this full info to the interface since some implementations may want to use the frequency or what have you to change how words are placed)

The complete() method is needed to finalise the Graphics/Graphics2D objects and write them to file.

The two implementations that exist for this currently are GaussianRotatingPlacerImpl.java and LogarithmicSpiralPlacerImpl.java

LogarithmicSpiralPlacerImpl.java uses a logarithmic spiral pattern to 'greedily' place the words on a spiral path growing out from 
the center point, dropping the word in the first open space.

GaussianRotatingPlacerImpl.java does a similar thing except that it merely uses a random number with a gaussian distribution from the centre
although I have set the variance to be far higher for the x axis than on the y axis, the variance for both increase with each failed placing attempt 
in order to improve performance instead of relying on crazy outliers.

This class uses the Graphics2D object instead of the regular Graphics Object in order to be able to rotate to new AffineTransforms and have 
some of the words vertical in the cloud.

#### CloudRenderer.java
This is a concrete class which provides a sort of facade pattern, it has the following constructor:

```java
public CloudRenderer(String source, SourceType sourceType, DrawingPattern pattern, String outputName)
```

(SourceType is an enum which will be used by the ParseableFactory (eager singleton) to decide which parseable implementation to return, similarly 
for DrawingPattern and the PlaceableFactory)

The class only has one method:

```java
public void draw()
```

3. How to Use the API
---

A simple runner class (Runner.java) is included as an example use: the main method for it is:

```java
public static void main(String args[]) throws Exception{		
		CloudRenderer warandpeace = new CloudRenderer("warandpeace.txt", SourceType.FILE, DrawingPattern.GAUSSIAN, "tolstoy");
		warandpeace.draw();
		
		CloudRenderer trump = new CloudRenderer("http://www.gutenberg.org/files/10/10-h/10-h.htm", SourceType.WEB, DrawingPattern.LOGARITHMIC_SPIRAL, "bible");
		trump.draw();
		
		CloudRenderer gmit = new CloudRenderer("http://www.gmit.ie/", SourceType.WEB, DrawingPattern.GAUSSIAN, "gmit");
		gmit.draw();
	}
```

This will output 3 png images named tolstoy.png, bible.png and gmit.png into the directory where your jar file is.

Please note: 

## *The runner class is slow because of the internet delay. If you use only local files it runs much faster.*

4. Tools and Environment Used
---
This project was developed in java and makes use of the [jsoup](http://jsoup.org/) library for pulling text from URLs.

![bible](http://oi68.tinypic.com/ins48m.jpg)

<a href="https://github.com/JohnMalmsteen"><img src="https://avatars1.githubusercontent.com/u/7085486?v=3&s=400" width="100px" height="100px" title="John" alt="John Image"/></a>
