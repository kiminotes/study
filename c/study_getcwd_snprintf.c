#include<stdio.h>
#include<unistd.h>
#include<string.h>


int main(int argc, char *argv[])
{
    char buf[1024];
    getcwd(buf, 1024);
    fprintf(stdout, "%s %d\n", buf, strlen(buf));
    snprintf(buf + strlen(buf), 
             strlen("proxy.conf") + 2/* one is for / and another is for '\0'*/, 
             "/%s", "proxy.conf");
    fprintf(stdout, "%s\n", buf);

    return 0;
}
