///usr/bin/env jbang "$0" "$@" ; exit $? # (1)

class helloworld { // (2)

    public static void main(String[] args) {
        if(args.length==0) {
            System.out.println("Hello World!");
        } else {
            System.out.println("Hello " + args[0]);
        }
    }
}