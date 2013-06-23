#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<errno.h>
#include<netinet/in.h>
#include<strings.h>

#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/tcp.h>
#include<netinet/ip.h>
#include<netinet/in.h>

#define SRC_PORT htons(23456)
#define DST_PORT htons(80)
#define INITIAL_SEQ htonl(88888888)

struct prseuheader
{
    unsigned long s_addr;
    unsigned long d_addr;
    unsigned char zero;
    unsigned char protocol;
    unsigned short len;
};

int sockfd;
struct sockaddr_in re_addr;
char buf [100]; 

    void 
usage()
{
    printf("Usage: %s \n", "syn local_ip dest_ip dest_port");
    exit(1);
}

    uint16_t
in_cksum(uint16_t *addr, int len)
{
    int             nleft = len;
    uint32_t        sum = 0;
    uint16_t        *w = addr;
    uint16_t        answer = 0;

    /*  
     * Our algorithm is simple, using a 32 bit accumulator (sum), we add
     * sequential 16 bit words to it, and at the end, fold back all the
     * carry bits from the top 16 bits into the lower 16 bits.
     */
    while (nleft > 1)  {
        sum += *w++;
        nleft -= 2;
    }   

    /* 4mop up an odd byte, if necessary */
    if (nleft == 1) {
        *(unsigned char *)(&answer) = *(unsigned char *)w ;
        sum += answer;
    }   

    /* 4add back carry outs from top 16 bits to low 16 bits */
    sum = (sum >> 16) + (sum & 0xffff); /* add hi 16 to low 16 */
    sum += (sum >> 16);         /* add carry */
    answer = ~sum;              /* truncate to 16 bits */
    return(answer);
}

    int
main(int argc, char** argv)
{

    int i;
    struct tcphdr* ptr_tcp;
    struct ip* ptr_ip;
    char chksum_buf[32];
    struct prseuheader p_header;
    int msg_len = sizeof(struct ip) + sizeof(struct tcphdr);

    if (argc < 4)
    {
        usage();
    }

    re_addr.sin_family = AF_INET;
    int port = atoi(argv[3]);
    re_addr.sin_port = htons(port);
    int ret = inet_aton(argv[2], &re_addr.sin_addr);
    if (ret == 0)
    {
        printf("illegal ip %s\n", argv[2]);
        exit(1);
    }

    bzero(buf, sizeof(buf));

    // fill in ip header
    ptr_ip = (struct ip*)buf;
    ptr_ip -> ip_v = IPVERSION;
    ptr_ip -> ip_hl = sizeof(struct ip) >> 2;
    ptr_ip -> ip_tos = 0;
    ptr_ip -> ip_len = htons(msg_len);
    ptr_ip -> ip_id = htons(0);
    ptr_ip -> ip_off = htons(IP_DF);
    ptr_ip -> ip_ttl = 64;
    ptr_ip -> ip_p = IPPROTO_TCP;
    ptr_ip -> ip_sum = htons(0);
    ptr_ip -> ip_dst = re_addr.sin_addr;;

    // fill in tcp header
    ptr_tcp = (struct tcphdr*)(buf + sizeof(struct ip));
    ptr_tcp -> source = SRC_PORT;
    ptr_tcp -> dest = htons(port);
    ptr_tcp -> seq = INITIAL_SEQ;
    ptr_tcp -> doff = 5 & 0xFF;
    ptr_tcp -> syn = 1;
    ptr_tcp -> window = htons(1024);
    ptr_tcp -> check = 0;

    sockfd = socket(AF_INET, SOCK_RAW, IPPROTO_RAW);

    if (sockfd == -1)
    {
        perror("failed to create raw socket");
        exit(1);
    }

    /*****/
    int on = 1;
    //设置IP数据包格式,告诉系统内核模块IP数据包由我们自己来填写
    ret = setsockopt(sockfd, IPPROTO_IP, IP_HDRINCL, &on, sizeof(on));
    if (ret == -1)
    {
        perror("failed to set socket option");
        exit(1);
    }
    /****/

    for(;;)
    {
        printf("send syn: ");
        fgetc(stdin);
        // ptr_ip -> ip_src.s_addr = random();

        ptr_ip -> ip_src.s_addr = inet_addr(argv[1]);

        // calculate checksum
        bzero(&p_header, sizeof(p_header));
        p_header.s_addr = ptr_ip -> ip_src.s_addr;
        p_header.d_addr = ptr_ip -> ip_dst.s_addr;
        p_header.zero = 0;
        p_header.protocol = IPPROTO_TCP;
        p_header.len = htons(20);

        ptr_tcp -> check = 0;

        bzero(chksum_buf, 32);
        memcpy(chksum_buf, &p_header, 12);
        memcpy(chksum_buf + 12, ptr_tcp, 20);
        ptr_tcp -> check =  in_cksum((uint16_t *)chksum_buf, 32);

        printf("checksum 0x%x\n", htons(ptr_tcp -> check));
        ret = sendto(sockfd, buf, msg_len, 0, (struct sockaddr*)&re_addr, sizeof(re_addr));
        if (ret == -1)
        {
            perror("failed to send message");
            sleep(1);
        }
        else 
        {
            printf("send %d bytes\n", ret);
        }
    }

    close(sockfd);

    printf("ok\n");

    return 0;
}
