1. pmd

	pmd -d pmd-demos\src\main\java\Test.java -f xml -R pmd-demos\src\main\resources\examples\excluding-ruleset-for-java.xml
	
	pmd -d pmd-demos\src\main\resources\examples\javascript.js -f csv -R ecmascript-basic,ecmascript-braces,ecmascript-unnecessary
	
2. a javascript example of cpd

	cpd --minimum-tokens 2 --language ecmascript --files pmd-demos\src\main\resources\examples\duplicated.js

3. cpd gui
	
	cpdgui.bat
	run.sh cpdgui # for *nux
