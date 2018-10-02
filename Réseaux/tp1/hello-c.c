#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

#define BUFFERMAX 128 /* taille maximale du buffer pour le message */

int main(int argc, char** argv){

    /* Déclaration des variables */
    
    struct sockaddr_in adr_serveur;           /* structure adresse du serveur */
    struct sockaddr_in adr_client;            /* structure adresse du client */
    socklen_t          adr_client_size;       /* taille de la structure */
    int                sock;                  /* socket de communication */
    int                num_ecrit;             /* taille des données envoyée */
    char               ip_serveur[BUFFERMAX]; /*ip du serveur, chaîne de char*/
    int                port;                  /* port de communication */
    ssize_t            ret;                   /* valeur de retour de fonction*/
    char*              message;               /* le message pour le serveur */
    

    /* on fixe des valeurs par defaut  */
    message = "Salut serveur !";
    port    = 8000;
    strcpy(ip_serveur, "127.0.0.1");
    
    
    /* Lecture des paramètres le la  ligne de commande */
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
    } /* fin de la gestion des paramètres en ligne de commande */
    

    /* Création de la socket de communication TCP */

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if(sock < 0){
	perror("Erreur de (socket)"); 
	exit(EXIT_FAILURE);
    }

    /* Initialisation de la structure representant l'adresse du serveur */

    memset(&adr_serveur, 0, sizeof(adr_serveur)); 
    adr_serveur.sin_family = AF_INET;
    adr_serveur.sin_port   = htons(port);
    inet_aton( ip_serveur, &adr_serveur.sin_addr );

    /* Initiation de la connexion au serveur*/  

    ret = connect(sock, (struct sockaddr *) &adr_serveur, sizeof(adr_serveur));
    if( ret == -1) {
	perror("Erreur de (connect)");
	exit(EXIT_FAILURE);
    }

    /* Récupération des informations sur la socket du client (IP, port) */

    adr_client_size = sizeof(adr_client);
    ret = getsockname(sock, (struct sockaddr *) &adr_client, &adr_client_size);
    if( ret == -1) {
	perror("Erreur de (getsockname)");
	exit(EXIT_FAILURE);
    }
    printf("Demande de connexion a (ip=%s, port=%d)\n\t depuis (ip=%s, port=%d)\n",
	   inet_ntoa(adr_serveur.sin_addr),  ntohs(adr_serveur.sin_port),
	   inet_ntoa(adr_client.sin_addr),  ntohs(adr_client.sin_port)
	);


    /* Envoi du message au le serveur */

    num_ecrit  = write(sock, message, strlen(message));
    if (num_ecrit != strlen(message)){
	perror("Erreur de (write)");
	exit(EXIT_FAILURE);
    }

    /* Fermeture de la socket */

    ret = close(sock);
    if ( ret == -1 ){
	perror("Erreur de (close)"); 
	exit(EXIT_FAILURE);
    }  
    
    return EXIT_SUCCESS;
}



