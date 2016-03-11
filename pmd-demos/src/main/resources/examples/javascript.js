// ForLoopsMustUseBraces
function foobar() {}
for (var i = 0; i < 42; i++)
	foobar();

function printNumbers() {
	for (var i = 0; i < 10; i ++) {
		if (true) {
			continue;
			// UnreachableCode
			console.log("========");
		}
	}
}

var foo, bar;

if (foo) {
	// Ok
}
if (bar) {
	// UnnecessaryBlock
	{
		// Bad
	}
}