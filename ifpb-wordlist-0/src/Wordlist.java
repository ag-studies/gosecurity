
/*
	[Arranjo(3,1)*Arranjo(3,1)]*Combinação(2,1)
	[(3!/(3-1)!)*(3!/(3-1)!]*[2!/1!(2-1)!) = 
	[3 * 3] * 2 = 18
	
	Sem repetição de elementos do mesmo conjunto
	A={1,2,3}
	B={a,b,c}
	n(S)=1 de A + 1 de B
	S={e(A),e(B)},{e(B),e(A)}
	
	Senha = A A
	        B B
	=============
	        A B = {1,2,3}x{a,b,c}
	        B A = {a,b,c}x{1,2,3}
	
	n(S)=18
*/
public class Wordlist {
	
	public static void main(String[] args) {
		//conjuntos
		String[] A = new String[]{"1","2","3"};
		String[] B = new String[]{"a","b","c"};
		//senha
		String[] AB = new String[9];
		String[] BA = new String[9];
		//resultado
		String[] S = new String[18];
		//
		int abIndex = 0, baIndex = 0;
		for (int aIndex = 0; aIndex < 3; aIndex++){
			for(int bIndex = 0; bIndex < 3; bIndex++){
				AB[abIndex++] = A[aIndex] + B[bIndex];
				BA[baIndex++] = B[bIndex] + A[aIndex];
			}
		}
		//
		for (int sIndex=0; sIndex<9; sIndex++){
			S[sIndex*2] = AB[sIndex];
			S[sIndex*2+1] = BA[sIndex];
		}
		//
		for (String s : S){
			System.out.print("{");
			System.out.print(s);
			System.out.print("},");
		}
	}

}
