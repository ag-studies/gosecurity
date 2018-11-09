
/*
	[Arranjo(3,2)*Arranjo(3,1)]*Combinação(3,2)
	[(3!/(3-2)!)*(3!/(3-1)!]*[3!/(2!*(3-2)!) = 
	[6 * 3] * 3 = 54
	
	Sem repetição de elementos do mesmo conjunto
	A={1,2,3}
	B={a,b,c}
	n(S)=2 de A + 1 de B
	S={e(A),e(A),e(B)},{e(B),e(A),e(A)},...
	
	Senha = A A A
	        B B B
	=============
	        A A B = {1}x{2,3}x{a,b,c}+{2}x{1,3}x{a,b,c}+{3}x{1,2}x{a,b,c}
	        B A A = {a,b,c}x{1}x{2,3}+{a,b,c}x{2}x{1,3}+{a,b,c}x{3}x{1,2}
	        A B A = {1}x{a,b,c}x{2,3}+{2}x{a,b,c}x{1,3}+{3}x{a,b,c}x{1,2}
	 
	{1,2,a},{1,2,b},{1,2,c},{1,3,a},{1,3,b},{1,3,c},
	{2,1,a},{2,1,b},{2,1,c},{2,3,a},{2,3,b},{2,3,c},
	{3,1,a},{3,1,b},{3,1,c},{3,2,a},{3,2,b},{3,2,c},
	
	{a,1,2},{a,1,3},{b,1,2},{b,1,3},{c,1,2},{c,1,3},
	{a,2,1},{a,2,3},{b,2,1},{b,2,3},{c,2,1},{c,2,3},
	{a,3,1},{a,3,2},{b,3,1},{b,3,2},{c,3,1},{c,3,2},
	
	{1,a,2},{1,a,3},{1,b,2},{1,b,3},{1,c,2},{1,c,3},
	{2,a,1},{2,a,3},{2,b,1},{2,b,3},{2,c,1},{2,c,3},
	{3,a,1},{3,a,2},{3,b,1},{3,b,2},{3,c,1},{3,c,2}
	
	n(S)=54
*/
public class Wordlist1 {

	public static void main(String[] args) {
		//conjuntos
		String[] A = new String[]{"1","2","3"};
		String[] B = new String[]{"a","b","c"};
		//senha
		String[] AAB = new String[18];
		String[] ABA = new String[18];
		String[] BAA = new String[18];
		//resultado
		String[] S = new String[54];
		//
		//[A0,A1,B]
		int aabIndex = 0, abaIndex = 0, baaIndex=0;
		for (int a0Index = 0; a0Index < 3; a0Index++){
			for (int a1Index = 0; a1Index < 3; a1Index++) {
				//saltar quando A0 == A1
				if (a0Index == a1Index) continue;
				//percorrer b
				for(int bIndex = 0; bIndex < 3; bIndex++){
					AAB[aabIndex++] = A[a0Index] + A[a1Index] + B[bIndex];
					ABA[abaIndex++] = A[a0Index] + B[bIndex] + A[a1Index];
					BAA[baaIndex++] = B[bIndex] + A[a0Index] + A[a1Index];
				}
			}
		}
		//não copiar aqui (só para mim)
		int tIndex = 0;
		for (String s : AAB){
			System.out.print("{");
			System.out.print(s);
			System.out.print("},");
			if (++tIndex % 6 == 0) System.out.println();
		}
		System.out.println("-----------------------------------");
		tIndex = 0;
		for (String s : ABA){
			System.out.print("{");
			System.out.print(s);
			System.out.print("},");
			if (++tIndex % 6 == 0) System.out.println();
		}
		System.out.println("-----------------------------------");
		tIndex = 0;
		for (String s : BAA){
			System.out.print("{");
			System.out.print(s);
			System.out.print("},");
			if (++tIndex % 6 == 0) System.out.println();
		}
		//imprimir todas as palavras
		for (int sIndex=0; sIndex<18; sIndex++){
			S[sIndex*3] =   AAB[sIndex];
			S[sIndex*3+1] = ABA[sIndex];
			S[sIndex*3+2] = BAA[sIndex];
		}
		for (String s : S){
			System.out.print("{");
			System.out.print(s);
			System.out.print("},");
		}
	}
}
