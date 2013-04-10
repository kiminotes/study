#include<stdio.h>
#include<string.h>


int main(int argc, char *argv[])
{
    char buf[1024];
    FILE *fp = fopen("./io.txt", "r");
    if (fp == NULL)
    {
        fprintf(stderr, "Failed to open file ./io.txt\n");
        return 1;
    }
    
    fgets(buf, sizeof(buf), fp);
    int len = strlen(buf);
    fprintf(stdout, "read %d chars\n", len);

    if (buf[len - 1] == '\n')
    {
        fprintf(stdout, "The last char readed is newline\n");
    }

    fclose(fp);

    return 0;
}
