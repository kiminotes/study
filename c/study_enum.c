#include<stdio.h>
#include<stdlib.h>


enum gender
{
    MAIL   = 1,
    FEMAIL = 2
};

int main(int argc, char *argv[])
{
    enum gender *g = NULL;
    g = malloc(sizeof(enum gender));
    *g = MAIL;
    printf("%d\n", *g);

    return 0;
}
