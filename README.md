# Alpha Recognizer

## Description
This project implements a software able to recognize hand-written characters. It has been developed for educational purposes and works through a simple Neural Network totally realized in Java.
The provided dataset is very small, but the program gives you the opportunity to generate other sample for the training set, so you can train the network on all the characters you want.
Then you can save the trained model into a file and use it whenever you want. This project does not work in a really advanced way and the network is very simple, so it does not work very well, but it's a very useful project to understand the functioning of neural networks and to provide an example on how they may be used.

## Used technologies
- Java is used to implement all the logic and to implement the neural network with a customizable structure;
- Java Swing for realizing the user interface.

## Usage
The software presents a very simple user interface which contains a canvas in which you can write a character. 
Then you can save the trained model giving a file name in which it must be stored and then clicking on the relative button.
You can also store the written character into the training set in order to expand it, giving the label name of the character and clicking the relative button.
All the recognized characters will appear in the appropriate box.

## Future improvements
Some operations must be done in order to improve character recognition:
- The training set must be expanded with a much larger collection of hand-written characters and the corresponding labels to train the model;
- The network could be replaced with a Convolutional Neural Network which will perform some convolutional operations on the input to extrapolate more representative informations, such as the characters edges, to do the predictions, instead of simply using the raw data.

## License
General Purpose License v3.0
