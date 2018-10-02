#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

#include "net_aux.h"

#define DEBUG
#define BUFFERMAX 100

int main(int argc, char** argv){

   
    int                sock;            /* socket de communication */
    char               ip_serveur[BUFFERMAX]; /*ip du serveur, chaine de char*/
    int                port;            /* port de communication */
    char*              message;         /* message pour le serveur */


    /* on fixe les valeurs par defaut  */
    port = 8000;
    strcpy(ip_serveur, "127.0.0.1");
    message="Salut serveur !";
    
    /* Lecture des paramettres en ligne de commande */
    if(argc > 1){
	int i=1;
	char *option;
	
	while (i<argc) {
	    option=argv[i++];
	    
	    if (strcmp(option,"-h")==0) {
		fprintf(stderr, "Utilisation : %s [-h] [-a ip serveur] [-p port serveur] \n", argv[0]);
		fprintf(stderr, "     -a : ip du serveur \n");
		fprintf(stderr, "     -p : port du serveur\n");
		fprintf(stderr, "     -m : message pour le serveur entre guillemets \n");
		fprintf(stderr, "     -h : cette aide \n");
		exit (EXIT_SUCCESS);

	    } else if  (strcmp(option,"-p")==0) { 	
		if (i==argc){
		    fprintf(stderr, "il manque le port du serveur\n");
		    exit(EXIT_FAILURE);
		}
		port = atoi(argv[i++]);

	    } else if  (strcmp(option,"-a")==0) {	
		if (i==argc){
		    fprintf(stderr, "il manque l'ip du serveur \n");
		    exit(EXIT_FAILURE);
		}
		strncpy(ip_serveur, argv[i++], BUFFERMAX);

	    } else if  (strcmp(option,"-m")==0) {	
		if (i==argc){
		    fprintf(stderr, "il manque le message \n");
		    exit(EXIT_FAILURE);
		}
		message = argv[i++];
	    } 

	    else {
		fprintf(stderr, "Option non roconue essayez -h \n");
		exit(EXIT_FAILURE);
	    }
	}
    }
    
    /* Création de la socket de communication */
    sock = create_socket();
    
    /* demande d'une connexion au serveur */
    open_connection(sock, ip_serveur, port);    

    /* envoi du message au le serveur */
    sock_send(sock, message);
 
    /* fermeture de la socket */
    close_connection(sock);

    return EXIT_SUCCESS;
}
