# Kēmu Kupu
## Preamble
This is a product of a project as part of the course SOFTENG 206, a required
course for Software Engineering Part II at the University of Auckland as of the
Academic Year 2021.  This project was overseen by lecturers and teaching
assistants from the Department of Electrical, Computer and Software Engineering
at the University of Auckland.

## About
**Kēmu Kupu** is a JavaFX application that tests the user on spelling Māori
words from a GUI.  The software project aims to help young adults aged 18 to 25
from abroad learn more about Māori words and their spelling.  The software is to
be used in conjunction with Festival, a Speech Synthesis System developed by
Alan W. Black, Paul Taylor and Richard Caley at the Centre for Speech Technology
Research (CSTR) at the University of Edinburgh, with significant contributions
from Carnegie Mellon University and other places.

## License
GNU General Public License Version 3 or later  
The program is provided with ABSOLUTELY NO WARRANTY, EXPRESS OR IMPLIED.

## Software Pre-Requisites
To run the software successfully, ensure that the following software have been
installed:
* Festival Speech Synthesis System
* Java Runtime Environment (JRE) 11
* JavaFX 11

To install packages, you may need root privileges.  In that case, prefix the
below commands with `sudo`.

Note that there are Māori voice packages for that can be used to enhance speech.
They can be obtained from the University of Auckland.

### Ubuntu/Debian (and derivatives)
`festival` is available from the default repositories.
```shell
apt install festival
```

JRE 11 is available as `openjdk-11-jre` from the default repositories.
```shell
apt install openjdk-11-jre
```

JavaFX is available as `openjfx` from the default repositories.
```shell
apt install openjfx
```

### Arch Linux (and derivatives)
`festival` is available from the default repositories.  Voices are available
with `festival-us` and `festival-english` packages.  See note above for Māori
voice packages.
```shell
pacman -S festival
```

JRE 11 is available as `jre11-openjdk` from the default repositories.
```shell
pacman -S jre11-openjdk
```

JavaFX is available as `java11-openjfx` from the default repositories.
```shell
pacman -S java11-openjfx
```

### Miscellaneous
One can get a copy of the JavaFX from https://gluonhq.com/products/javafx/.

## Running
### Using `run.sh`
Assuming that one has downloaded the JavaFX JAR files onto the following
directory:
```
/home/student/javafx-sdk-11.0.2/javafx
```
then one can proceed with this set of instructions for running.

First, ensure that `run.sh` is given executable permissions for the current
user.
```shell
chmod +x run.sh
```

Then to start the application, simply run the `run.sh` file from the shell, like
so:
```shell
./run.sh
```

### Manually
To run the program, first look at where the JavaFX files are installed by
performing:
```shell
whereis openjfx
```

The result of the above command on Ubuntu 20.04 LTS is:
```
openjfx: /usr/share/openjfx
```

As such, the path to the JavaFX JARs will be in the `lib` directory inside the
path returned by the shell command:
```
/usr/share/openjfx/lib
```

In a shell window, perform the following command:
```shell
export JAVAFX_PATH=/path/to/openjfx/lib
```
where `/path/to/openjfx` is the path returned by calling `whereis openjfx` on
your system.

Then run the following command:
```shell
java -Djdk.gtk.version=2 --module-path "$JAVAFX_PATH" --add-modules javafx.controls,javafx.media,javafx.base,javafx.fxml -Dfile.encoding=UTF-8 -jar triple-js-final.jar
```
where `$JAVAFX_PATH` is the shell variable assigned before this command.

