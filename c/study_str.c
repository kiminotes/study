#include <stdio.h>
#include <string.h>


int main(int argc, char *argv[])
{
    char buf[20];
    strcpy(buf, "hello");

    fprintf(stdout, "%s %d\n", buf, strlen(buf));

    return 0;
}
