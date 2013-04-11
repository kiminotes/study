#include <stdio.h>
#include <sys/time.h>
#include <unistd.h>


static long timeval_diff(const struct timeval *t1, const struct timeval *t0)
{
    long int result = t1 -> tv_usec;
    long int t1_sec = t1 -> tv_sec;
    if (t1 -> tv_usec < t0 -> tv_usec)
    {
        t1_sec -= 1;
        result += 1000000;
    }
    result -= t0 -> tv_usec;
    result += (t1_sec - t0 -> tv_sec) * 1000000;
    return result;
}


int main(int argc, char *argv[])
{
    struct timeval t1, t0;
    gettimeofday(&t0, NULL);
    int sleep_sec = 10;
    fprintf(stdout, "sleep %d seconds\n", sleep_sec);
    sleep(sleep_sec);
    gettimeofday(&t1, NULL);

    long int diff = timeval_diff(&t1, &t0);

    fprintf(stdout, "t0 = %ld %d\n", t0.tv_sec, t0.tv_usec);
    fprintf(stdout, "t1 = %ld %d\n", t1.tv_sec, t1.tv_usec);
    fprintf(stdout, "t1 - t0 = %ld\n", diff);
    
    diff = timeval_diff(&t0, &t1);
    fprintf(stdout, "t0 - t1 = %ld\n", diff);

    diff = timeval_diff(&t1, &t1);
    fprintf(stdout, "t1 - t1 = %ld\n", diff);

    return 0;
}
