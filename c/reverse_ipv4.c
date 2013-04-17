#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>


static char* reverse_ipv4(char *ip)
{
    if (NULL == ip)
    {
        return ip;
    }

    int len = strlen(ip);
    char *result = malloc(len + 1);
    *(result + len) = '\0';
    int start = 0;
    int pos = 0;
    int rev_pos = len;
    int dot_num = 0;
    int ch_num = 0;
    while(dot_num < 3)
    {
        if (isdigit(*(ip + pos)) == 0 && '.' != *(ip + pos))
        {
            free(result);
            return NULL;
        }
        
        if ('.' == *(ip + pos)) {
            strncpy(result + rev_pos, ip + start, ch_num);
            rev_pos--;
            *(result + rev_pos) = '.';
            pos++;
            ch_num = 0;
            start = pos;
            dot_num++;
        } 
        else 
        {
            ch_num++;
            pos++;
            rev_pos--;
        }
    }

    strncpy(result, ip + pos, len - pos);

    return result;
}


int main(int argc, char *argv)
{
    printf("%s\n", reverse_ipv4("12.34.56.78"));
}
