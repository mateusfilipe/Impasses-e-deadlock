package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class main {
	public static void main(String[] args) throws IOException {
		try {
			FileReader arq = new FileReader("teste.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			String[] processos;
			String[] primeiraLinha = (lerArq.readLine()).split(" ");

			ArrayList<String> processoP = new ArrayList<String>();
			ArrayList<String> processoR = new ArrayList<String>();
			ArrayList<Integer> ListaAux = new ArrayList<Integer>();
			ArrayList<String> ListaLetra = new ArrayList<String>();

			int nLinhas = Integer.parseInt(primeiraLinha[0]);
			int nProcessos = Integer.parseInt(primeiraLinha[1]);

			for (int i = 0; i < nLinhas; i++) {
				linha = lerArq.readLine();
				processos = linha.split(";");
				processoP.add(processos[0]); // possuido
				processoR.add(processos[1]); // requisitado
			}

			for (int i = 0; i < nLinhas; i++) {
				
				System.out.println((i) + " quer: " + processoR.get(i));

				for (int j = 0; j < nLinhas; j++) {
					if (processoR.get(i).length() == 2) { // se n�o tem dois requisitos
						if (processoR.get(i).trim().equals(processoP.get(j).trim())) {
							System.out.println("   " + (j) + " tem: " + processoP.get(j));
							ListaLetra.add(processoR.get(i).trim());
							i = j-1;
							ListaAux.add(i);
							break;
						}
					}
				}
				if (countDuplicates(ListaAux, i+1)) {
					break;
				}
			}
			//ListaAux.remove(ListaAux.size()-1);
			Collections.sort(ListaAux);
			//ListaLetra.remove(ListaLetra.size()-1);
			
			System.out.println(ListaAux.toString());
			System.out.println(ListaLetra.toString());

			lerArq.close();

			FileWriter fw = new FileWriter( "saida.txt" );
			BufferedWriter bw = new BufferedWriter( fw );
			for(int i = 0 ; i < 3 ; i++) {
				bw.write((ListaAux.get(i)+2) + " " + ListaLetra.get(i) + " " );
			}
			bw.close();
			fw.close();

			
		} catch (IOException e) {
			System.err.printf("N�o foi poss�vel abrir o arquivo.\n" + e.getMessage());
		}
	}
	
	public static Boolean countDuplicates(ArrayList<Integer> list, int e) {
		int nRepete = 0;
			if (list.contains(e)) {
				System.out.println(e);
				nRepete++;
			}
		if (nRepete == 1) {
			return true;
		} else
			return false;
	}

}