#define STR(x)   #x
#define XSTR(x)  STR(x)

XSTR(hello world)

#define xstr(s) str(s)
#define str(s) #s
#define foo 4

str (foo)  // outputs "foo"
xstr (foo) // outputs "4"
