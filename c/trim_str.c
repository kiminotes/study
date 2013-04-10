#include <stdio.h>
#include <string.h>
#include <stdlib.h>

static char *trim(char *str)
{
    char *start, *end, *sp, *ep;
    start = sp = str;
    end = ep = str + strlen(str) - 1;
    while((sp <= end) && (isspace(*sp) || isblank(*sp))) { ++sp; }

    while((start < ep) && (isspace(*ep) || isblank(*ep))) { --ep; }

    if (sp <= ep)
    {
        int len = ep - sp + 1;
        char *result = malloc(len + 1);
        strncpy(result, sp, len);
        *(result + len) = '\0';
        return result;
    }
    else 
    {
        return NULL;
    }
}

static void print(const char *str)
{
    if (str != NULL)
    {
        printf("XXX%sXXX\n", str);
    }
    else 
    {
        printf("null\n");
    }
}

int main(int argc, char *argv[])
{
    char *str = trim("  ");
    if (str == NULL) {
        printf("null\n");
    }

    str = trim("   a    ");
    print(str);

    str = trim("   a");
    print(str);

    str = trim("a   ");
    print(str);

    return 0;


}
