[http://stackoverflow.com/questions/24482259/eclipse-issue-with-maven-build-and-jdk-when-generating-qclasses-in-querydsl](http://stackoverflow.com/questions/24482259/eclipse-issue-with-maven-build-and-jdk-when-generating-qclasses-in-querydsl)

#### SOLUTION 1

Following this link

> Note:
> "The Maven APT plugin has a known issue that prevents its usage directly from Eclipse. Eclipse users must create the Querydsl query types manually by running the command `mvn generate-sources` at command prompt."
> So i execute the command line mvn generate-sources in my project floder with console cmd and i got my Qclasses generated.

#### SOLUTION 2 from @informatik01 comment

we can explicitly specified JVM in the eclipse.ini like that :

		-vm
		C:\Program Files\Java\jdk1.7.0_45\bin\javaw.exe

		-vmargs

The -vm option must occur before the -vmargs option and for more info read @informatik01 comment below.