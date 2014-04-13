import java.util.Arrays;

public class HeapSort {

public static int heapSize;
public static int LEFT(int i)
{
    //retorna o  �ndice a esquerda de uma matriz baseada �ndice zero
    return 2*i+1;
}

public static int RIGHT(int i)
{
    //retorna o �ndice direito de uma matriz com base zero
    return 2*i+2;
}


public static void BUILD_MAX_HEAP(int A[])
{
    heapSize = A.length;//heap size inicializado
    for(int i=A.length/2; i>=0;i--)
    {
        MAX_HEAPIFY(A, i);
    }
}


public static void MAX_HEAPIFY(int A[],int i)
{
    int l=LEFT(i);//o �ndice do elemento esquerda que � 2 * i +1 (para zero, matriz indexada com base)
    int r=RIGHT(i);//indicador direito = 2 * i +2;
    int largestElementIndex = -1;
    //index n�o pode ser negativo assim inicializar o maior �ndice, ele vai ser usado mais tarde
    
    if(l<heapSize && A[l]>A[i]){
        largestElementIndex = l;
    }
    else //se a propriedade de heap m�ximo n�o seja violada c�pia �ndice da raiz em largestElementIndex
    {
        largestElementIndex=i;
    }
    //verifique a sub �rvore certa para o m�ximo propriedade pilha viola��o
    //largestElementIndex � calculado a partir do passo anterior
    if(r<heapSize && A[r]>A[largestElementIndex])
    {
        largestElementIndex = r;
    }
    //se a raiz n�o tem o maior �ndice, em seguida, trocar o maior elemento com o elemento raiz

    if(largestElementIndex!=i)
    {
        int temp = A[i];
        A[i]=A[largestElementIndex];
        A[largestElementIndex]=temp;
        //depois de swap, de forma recursiva chamar a MAX_HEAPIFY na maior �ndice (elemento raiz)
        MAX_HEAPIFY(A, largestElementIndex);
    }

}

public static void HEAP_SORT(int A[])
{
    //max heap � constru�do com HEAPSIZE inicializado
    BUILD_MAX_HEAP(A);
    //a partir de circuito final atrav�s matriz inteira
    for(int i=A.length-1;i>=0;i--)
    {
        int temp = A[0];
        A[0]=A[i];
        A[i]=temp;
        //reduzir a janela de pilha por um
        heapSize  = heapSize-1;
        //chamar max heapify na pilha reduzida
        MAX_HEAPIFY(A,0);
    }
}

public static void main(String[] args) {


    int A[] = new int[]{4,1,3,2,5,9,10,6,8,7};
    HEAP_SORT(A);
    System.out.println(Arrays.toString(A));


}

}