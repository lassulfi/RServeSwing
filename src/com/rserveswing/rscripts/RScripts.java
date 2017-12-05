/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rserveswing.rscripts;

import java.io.IOException;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * Classe que contém a conexão e os Scripts executados pelo R
 * @author luis.assulfi
 */
public class RScripts {
    
    //Atributos
    private static RConnection connection;
    private Process p;
    
    //Métodos especiais
    public RScripts() {
        
        try {
            //Cria um processo que executa o Rserve
            p = Runtime.getRuntime().exec("R CMD Rserve() --vanilla");
                       
            //Cria a conexão com o R
            connection = new RConnection();
            String connectionSource = "source('C:/RStudio/MyScript.R')";
            connection.eval(connectionSource);    
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RserveException e){
            e.printStackTrace();
        }    
    }

    //Métodos
    public static int myAdd(int num1, int num2){
    
        //Script para executar a função
        String myScript = "myAdd(" + num1 + "," + num2 + ")";
             
        try {
            
            //Execução do Script
            int result = connection.eval(myScript).asInteger();
            return result;
            
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }catch (RserveException e){
            e.printStackTrace(); 
        }
        
        return 0;
    }
    
}
