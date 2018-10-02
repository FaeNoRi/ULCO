#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <signal.h>

#include "net_aux.h"


#define DEBUG

#define BUFFERMAX 100


int  sock; /* la socket d'écoute */
/* déclarée en globale pour qu'elle soit visible depuis quitproc() */


/* procédure exécutée lors du ctrl-C */
void quitproc(){
    
    /* On ferme la socket d'écoute de connexion */
    close_connection(sock);

#ifdef DEBUG
    printf("Serveur arrete");
#endif

    exit(EXIT_SUCCESS);
}


/* procedure principale */
int main(int argc, char** argv){
    
    char buf[BUFFERMAX];  /* buffer pour les données reçues*/
    int  sock_effective;  /* la socket de communication */

    int port = 8000;
    char *ip_serveur="127.0.0.1"; 

    /* indiquer au systeme que faire en cas de ctrl-c */
    signal(SIGINT, quitproc);
    signal(SIGQUIT, quitproc);


    /* Lecture des paramètres en ligne de commande */
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
    
    /* création de la socket de communication */
    sock = create_socket();

    /* initialisation de la structure representant l'adresse */
    start_server(sock, ip_serveur, port);

    while(1){
	
	/* Attendre les requêtes de connexion */
	sock_effective = wait_connection(sock);
   
	/* Lecture du message du client */
	sock_receive(sock_effective, buf, BUFFERMAX);
	
	printf("Reception du message : %s\n", buf);
	
	/* Fermeture de la socket avec le client */
	close_connection(sock_effective);
		
    }

    return EXIT_SUCCESS;
}
