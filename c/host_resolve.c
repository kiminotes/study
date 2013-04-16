#include <stdio.h>
#include <netdb.h>
#include <stdlib.h>


int main(int argc, char *argv[])
{

    if (argc < 2)
    {
        fprintf(stdout, "Usage: %s query_string\n", argv[0]);
        exit(1);
    }

    struct hostent *ent = gethostbyname(argv[1]);

    if (NULL == ent)
    {
        if (HOST_NOT_FOUND == h_errno)
        {
            fprintf(stdout, "HOST NOT FOUND\n");
        }
        else if (NO_ADDRESS == h_errno || NO_DATA == h_errno)
        {
            fprintf(stdout, "NO DATA\n");
        }
        else 
        {
            fprintf(stdout, "%d\n", h_errno);
        }
    }
    else 
    {
        fprintf(stdout, "Found\n");
    }

    return 0;
}
