# Regular expressions

You should define 3 regular expression patterns. The patterns will be checked by selecting sub strings from example texts.

#### Example

The patterns should be stored in a variable which is already defined for the single tasks. For example, imagine that we are asked to define a pattern in variable `example` that can be used to search a string that exactly matches `abc`. We would define the following variable:
```java
String examplePattern = null;
examplePattern = "abc";
```
Note that the pattern is written as a Java string, i.e., it might be necessary to escape certain characters. For example, if our pattern should contain the newline character `\n` it has to be escaped as `"\\n"`.

#### Hints

- Unfortunately, not all engines which are able to process regular expressions offer the same character classes. Therefore, it is a good idea to read about the engine you are using and its abilities. For our tests, we are relying on the standard [Pattern class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html) class of Java.
  
## Task 1

Define a pattern for selecting all abbreviations. You can assume that an abbreviation will comprise only capital letters.

The example sentence for the example test is:
> The IEE shouldn't be confused with the IEEE. The latter was found in 1871 and merged with the IERE, IIE and IET.
```java
String pattern1 = null;
// YOUR CODE HERE
pattern1 = "[A-Z][A-Z][A-Z]*";
```
## Task 2

Define a pattern for selecting all words that have `"the"` as a _true subset_, i.e., words that contain it as a substring but have at least one more character.

The example sentence for the test is:
> There was a theologian named Aristides the Athenian.

Please make sure that only the words are selected without leading or trailing whitespaces or punctuation characters.
```java
String pattern2 = null;
// YOUR CODE HERE
pattern2 = "[Tt][h][e][a-z]+|[A-z]+[t][h][e][a-z]*";
```
## Task 3

Define a pattern for selecting all words that have the substring `"the"` but not `"theo"`. Note that in contrast to the previous task, `"the"` does not have to be a true subset, i.e., the word `"the"` should be selected as well.

The example sentence for the test is:
> There are theologians discussing theories about the creation of the world.

Note that the tests do _not_ contain the special case words that have both substrings, e.g., `"theotherapy"` containing `"theo"` and `"the"` is excluded from tests.

Please make sure that only the words are selected without leading or trailing whitespaces or punctuation characters.
```java
String pattern3 = null;
// YOUR CODE HERE
pattern3 = "[A-z]*[Tt][Hh][Ee](?![oO])[A-z]*";
```
## Task 4

Define a pattern for selecting all words containing at least three times the character `a` (including its uppercase variant `A`).

The example sentence for the test is:
> Anastasia would like to have a banana split.
```java
String pattern4 = null;
// YOUR CODE HERE
pattern4 = "[B-z]*[aA][B-z]*[aA][B-z]*[aA][A-z]*";
```