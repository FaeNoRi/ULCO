#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUFFERMAX 128 /* taille maximale du buffer pour le message */

int main(int argc, char** argv){
    
    struct sockaddr_in adr_serveur;     /* structures de données contenant */
    struct sockaddr_in adr_client;      /* les adresses du client et serveur */

    socklen_t          adr_client_size; /* taille de l'adresse du client */
    char               buf[BUFFERMAX];  /* buffer pour les données reçues*/
    ssize_t            num_lues;        /* taille des donnees lues */
    
    int                sock;            /* la socket initiale (d'écoute) */
    int                sock_effective;  /* la socket de communication */
    int                ret;             /* valeur de retour des fonctions */

    int                port = 8000;            /* port d'écoute */ 
    char              *ip_serveur="127.0.0.1"; /* ip d'écoute */

    /* Lecture des paramettres en ligne de commande */
    if(argc > 1){
	int i=1;
	char *option;
	
	while (i<argc) {
	    option=argv[i++];
	    
	    if (strcmp(option,"-h")==0) {
		fprintf(stderr, "Utilisation : %s [-h] [-a ip serveur] [-p port serveur] \n", argv[0]);
		fprintf(stderr, "     -p : port du serveur\n");

		fprintf(stderr, "     -h : cette aide \n");
		exit (EXIT_SUCCESS);

	    } else if  (strcmp(option,"-p")==0) { 	
		if (i==argc){
		    fprintf(stderr, "il manque le port du serveur\n");
		    exit(EXIT_FAILURE);
		}
		port = atoi(argv[i++]);

	    } 
	    else {
		fprintf(stderr, "Option non roconue essayez -h \n");
		exit(EXIT_FAILURE);
	    }
	}
    }
    
    /* Création de la socket d'écoute *****************************************/

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if(sock < 0){
	perror("Erreur de (socket)"); 
	exit(EXIT_FAILURE);
    }
    
    /* Initialisation de la structure représentant l'adresse ******************/

    memset( &adr_serveur, 0, sizeof(adr_serveur)); /* on la met a zéro */
    adr_serveur.sin_family = AF_INET;              /* on fixe le protocole IP */
    adr_serveur.sin_port   = htons(port);          /* on fixe le port  */
    inet_aton(ip_serveur, &adr_serveur.sin_addr);  /* on fixe l'IP d'écoute */
    
    /* Association de l'adresse IP locale a la socket d'écoute */

    ret = bind( sock, (struct sockaddr*) &adr_serveur, sizeof(adr_serveur) );
    if( ret == -1 ) {
	perror("Erreur de (bind)"); 
	exit(EXIT_FAILURE);
    }
    printf("Demarrage du serveur, et ecoute sur (ip: %s, port: %d)\n", 
	   inet_ntoa(adr_serveur.sin_addr),
	   ntohs(adr_serveur.sin_port));
    
    /* Préparation de la socket a l'écoute  */

    ret = listen(sock, 2);
    if( ret == -1 ) {
	perror("Erreur de (listen)"); 
	exit(EXIT_FAILURE);
    }  
    
    /* Attente d'une demande de connexion *************************************/


    /* taille de la struct adr du client */
    adr_client_size = sizeof(adr_client); 

    /* Si la connexion est faite la communication passe par sock_effective */
    sock_effective = accept( sock, (struct sockaddr*) &adr_client, 
			     &adr_client_size);
    if ( sock_effective == -1 ){
	perror("Erreur de (accept)"); 
	exit(EXIT_FAILURE);
    } 
    printf("Demande de connexion depuis (ip : %s, port : %d)\n",   
	   inet_ntoa(adr_client.sin_addr),
	   ntohs(adr_client.sin_port));
 

    /* Lecture depuis la socket ***********************************************/

    num_lues = read( sock_effective, buf, BUFFERMAX);
    if ( num_lues == -1 ){
	perror("Erreur de (read)"); 
	exit(EXIT_FAILURE);
    }  
    
    buf[num_lues] = '\0'; /* on termine la chaîne par un caractère NULL */	
    printf("Reception du message : %s\n",  buf);

    /* Fermeture de la socket avec le client  */
    
    ret = close(sock_effective);
    if ( ret == -1 ){
	perror("Erreur de (close)"); 
	exit(EXIT_FAILURE);
    }  
    
    /* Fermeture de la socket d'écoute  */
    
    ret = close(sock);
    if ( ret == -1 ){
	perror("Erreur de (close)"); 
	exit(EXIT_FAILURE);
    }  
    
    
    return EXIT_SUCCESS;
}
