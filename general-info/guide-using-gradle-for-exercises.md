# Mini-guide on using Gradle for exercises

**NOTE: The content of this page is preliminary and subject to changes**

We use the build tool [Gradle](https://gradle.org/) for compiling and running the code in this course.
This is because Gradle offers many advantages: easy and uniform way to import libraries, uniform compilation and execution environment in different OSs, and compatibility with popular IDEs. 

Furthermore, we will use Java version 8 or higher.

Below we explain how to install Gradle and the Java Development Kit (JDK), run Gradle projects and create your own Gradle project to write code from scratch. We describe the process for 3 OSs: Ubuntu 20.04, Windows 10 and MacOS. Most likely this guide applies to other versions of these OSs, and to other Linux distributions. The guide focuses on Gradle 7.1.1, but the steps below should work for newer versions of Gradle up to 7.5 (note that we do not use Gradle 8 in this course). If you have problems following these steps do not hesitate to contact us.

## Installing Java JDK 8 or higher and Gradle 7.1.1

1. Download and install java JDK 8 or higher (see below, if you already have Java installed). We recommend openjdk (e.g., [https://jdk.java.net/16/](https://jdk.java.net/16/)).
   - <u>Ubuntu 20.04</u>: 
	 1. `$ sudo apt install openjdk-XXX-jdk` with `XXX` ≥ 8 (e.g., `openjdk-16-jdk`).
   - <u>Windows 10</u>:
     1. Download zip from [https://jdk.java.net/16/](https://jdk.java.net/16/).
     2. Unzip in an appropriate directory. Let's denote it as `/path/to/jdk/`.
     3. Add to your `Path` variable the path to the `bin` folder, i.e., `/path/to/jdk/bin`.
		1. Open "Edit the system environment variables".
		2. Select "Environment Variables...".
        3. Double-click in the `Path` variable.
        4. Click "New".
		5. Add the path to unzipped `bin` folder, i.e., `/path/to/jdk/bin` (replace `/path/to/jdk/bin` with the actual path to the folder you unzipped).
   - <u>MacOS</u>:
     1. Download zip from [https://jdk.java.net/16/](https://jdk.java.net/16/).
     2. Unzip in an appropriate directory. Let's denote it as `/path/to/jdk` <!-- ~/PCPP/jdk-16.0.2.jdk -->
     3. Append `/Contents/Home` to the path above (i.e., `/path/to/jdk/Contents/Home`) and set this as your `JAVA_HOME` path.
		1. We explain how to set up this variable using `zsh`. 
		   Edit `$HOME/.zshrc` to include `export JAVA_HOME=/path/to/jdk/Contents/Home`. You may need to create this file if it does not exist.
		   Remember that, for changes to take effect, you should start a new terminal or run `source ~/.zshrc`.

2. Download and install Gradle 7.1.1 (see [https://gradle.org/install/](https://gradle.org/install/) | Installing Manually)
   
   - <u>Ubuntu 20.04</u>
	 1. Download Gradle 7.1.1 from [https://gradle.org/releases/](https://gradle.org/releases/).
	 2. Unzip in a directory of your choice. Let's denote it as `/path/to/gradle/`.
	 3. Set your path variable to include the `bin` directory.
		 1. Edit `$HOME/.bashrc` with `export PATH=/path/to/gradle/bin:$PATH` (replace `/path/to/gradle/bin` with the actual path to the folder on your machine).
		    Remember that, for changes to take effect, you should start a new terminal or run `source ~/.bashrc`.  
   - <u>Windows 10</u>
	 1. Download Gradle 7.1.1 from [https://gradle.org/releases/](https://gradle.org/releases/).
	 2. Unzip in a directory of your choice.
	 3. Set your path variable to include the `bin` directory
		 1. Open "Edit the system environment variables".
		 2. Select "Environment Variables...".
		 3. Double-click in the `Path` variable.
		 4. Click "New", 
		 5. Add the path to unzipped `bin` folder.
   - <u>MacOS</u>: (Alternatively, if you use brew, you can install gradle with `brew install gradle` and skip the steps below)
	 1. Download Gradle 7.1.1 from [https://gradle.org/releases/](https://gradle.org/releases/).
	 2. Unzip in a directory of your choice. Let's denote it as `/path/to/gradle/`.
	 3. Append `/bin` to the path above (i.e., `/path/to/gradle/bin`) and add this to your PATH.
		 1. Edit `$HOME/.zshrc` with `export PATH=/path/to/gradle/bin:$PATH` (replace `/path/to/gradle/bin` with the actual path to the folder on your machine). Remember that, for changes to take effect, you should start a new terminal or run `source ~/.bashrc`.  


### Adjusting path variables (Windows) if you already have Java installed

If you already have Java installed:
1. Check that it uses JDK 8 (or higher)
2. Check that the environment variable `JAVA_HOME` is set correctly as explained in the guide: https://explainjava.com/set-java-path-and-java-home-windows/


## Running the exercises in Gradle

1. Clone the course repository `$ git clone https://github.itu.dk/jst/PCPP2022-public.git`
1. Navigate to the exercises folder for first week, e.g., `$ cd week01/code-exercises/week01exercises/`.
2. Execute the desired program by running `$ gradle -PmainClass=<package>.<java_class> run`, e.g., `$ gradle -PmainClass=exercises01.TestLongCounterExperiments run`.
   - Note that `<java_class>` should include a `main()` method.
   - This step implicitly compiles the code. You can compile only (without executing) by running `$ gradle compileJava`. No need to specify a class; this command compiles all files in `app/src/main/java`.
   
### Deprecation warning

Please ignore the following deprecation warning.

```
Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/7.2/userguide/command_line_interface.html#sec:command_line_warnings
```

The reason for the warning is that we are using a function that will be deprecated in Gradle 8.0 (not released yet).
This is the function that allows us to select the class to run using the flag `PmainClass=<package>.<java_class>`.
We have tried to use the recommended function that will be supported in Gradle 8.
However, with the new function, we lose backwards compatibility with older versions of Gradle.
Consequently, we prefer to support current and older version of Gradle, as opposed to fix problems with yet-to-be-released versions of Gradle.


**Switching off warnings**. If this deprecation warning disturbs you, it is possible to switch off warnings by appending `--warning-mode none` to the running command above. For instance, `$ gradle -PmainClass=<package>.<java_class> run --warning-mode none`.
You can also create a file `grale.properties` in the root of the project with the line `org.gradle.warning.mode=none`.
However, note that switching off warnings may result in missing important information to debug your code.
So, if you switch warnings off and you are struggling in compiling or running code, consider switching them on temporarily.


## Create a Gradle project of your own

Sometimes you may want to start a Gradle project from scratch. To this end, we recommend following this official Gradle documentation [https://docs.gradle.org/current/samples/sample_building_java_applications.html](https://docs.gradle.org/current/samples/sample_building_java_applications.html).

In summary, when running `$ gradle init`, you should select:

- Type of project: `2. application`
- Implementation language: `3. Java`
- Functionality across multiple subprojects: `1. no - only...`
- Build script DSL: `1. Groovy`
- Test framework: `1. JUnit 4`

Finally, select project name and source package of your choice.

### Minor differences in running the project

Note that in the official Gradle guide they suggest to run the project using `$ ./gradlew run`. This is equivalent to our command above. So you should be able to run the new project using `gradle run`.
Note also that in this newly created project it is not necessary to specify the flag `PmainClass`. This is because the project has only one entry point in the automatically generated class.

