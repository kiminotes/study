#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LINE_MAX_LENGTH 1024

struct dns_conf_line
{
    char *line;
    struct dns_conf_line *next;
};

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        fprintf(stderr, "Usage: %s file_to_read\n", argv[0]);
        exit(1);
    }

    FILE *fp = fopen(argv[1], "r");
    if (NULL == fp)
    {
       perror(argv[1]);
       exit(1);
    }

    struct dns_conf_line *conf = (struct dns_conf_line*)malloc(sizeof(struct dns_conf_line)); 
    conf->line = NULL;
    conf->next = NULL;

    struct dns_conf_line *cur = conf;
    struct dns_conf_line *added = NULL;
    char buf[LINE_MAX_LENGTH];
    while(feof(fp) == 0 && (fgets(buf, LINE_MAX_LENGTH, fp) != NULL))
    {
        // trim
        added  = (struct dns_conf_line*)malloc(sizeof(struct dns_conf_line));
        added->line = malloc(strlen(buf) + 1);
        strcpy(added->line, buf);
        added->next = NULL;
        cur->next = added;
        cur = added;
    }

    fclose(fp);
    fp = NULL;

    cur = conf->next;
    while(cur != NULL)
    {
        if (cur->line != NULL)
        {
            fprintf(stdout, "%s", cur->line);
        }
        cur = cur->next;
    }

    while(conf != NULL)
    {
        cur = conf;
        conf = cur->next;
        free(cur->line);
        cur->line = NULL;
        free(cur);
        cur = NULL;
    }

    return 0;
}
