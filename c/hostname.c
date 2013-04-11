#include <stdio.h>
#include <unistd.h>
#include <sys/utsname.h>


int main(int argc, char *argv[])
{
    char buf[2014];
    gethostname(buf, 1023);
    fprintf(stdout, "%s\n", buf);
   
    struct utsname name;
    uname(&name);
    fprintf(stdout, "sysname %s\n", name.sysname);
    fprintf(stdout, "nodename %s\n", name.nodename);

    return 0;
}
