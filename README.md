![Travis](https://travis-ci.org/WellCosta/MathMax.svg?branch=master) [![codecov](https://codecov.io/gh/WellCosta/MathMax/branch/master/graph/badge.svg)](https://codecov.io/gh/WellCosta/MathMax)

# MathMax                                 
Arithmetic expression evaluator for Java

### Usage

```java
// Instantiate an Evaluator
final Evaluator evaluator = Evaluator.create();
// Pass String with expression to be interpreted
final double answerToUniverse = evaluator.evaluate( "6 * 9" );
// Yields 54.0 (which is 42.0 is base 13)
System.out.println( answerToUniverse );
```

### Running Tests

The test execution entry point is [AllTests](https://github.com/WellCosta/MathMax/blob/master/src/test/java/com/github/mathmax/main/AllTests.java). 
This suite centralizes the full test execution. 
