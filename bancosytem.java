import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
class Conta{
    String titular;
    double saldo;
    String senha;
    public Conta(String t,double s,String s2){
        this.titular = t;
        this.saldo=s;
        this.senha=s2;
    }
    ArrayList<String> historico = new ArrayList<>();
    public void historico(String mov){
        historico.add(mov);
    }
    public void extrato(){
        System.out.println("===EXTRATO===");
        for(String r : historico){
            System.out.println(r);
        }
    }
    public double getSaldo(){
        return saldo;
    }
    public String getSenha(){
        return senha;
    }
    public String getTitular(){
        return titular;
    }
    public void mostrar(){
        System.out.println("________________");
        System.out.println(titular);
        System.out.println(saldo);
        System.out.println(senha);
    }
}
class Banco{
    HashMap<Integer,Conta> lista = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    int id = 0;
    public void menu(){
        int op=1;
        while(op!=0){
        System.out.println("===BANCO===");
        System.out.println("1 criar conta");
        System.out.println("2 depositar");
        System.out.println("3 sacar");
        System.out.println("4 transferir");
        System.out.println("5 ver conta");
        System.out.println("6 listar contas");
        System.out.println("7 extrato");
        System.out.println("0 sair");
        op = sc.nextInt();
        sc.nextLine();
        if(op>7 || op<0){
            System.out.println("________________");
            System.out.println("opçao invalida");
        }
            switch(op){
                case 1: criar();
                break;
                case 2: depositar();
                break;
                case 3 : sacar();
                break;
                case 4 : transferir();
                break;
                case 5 : verConta();
                break;
                case 6: listar();
                break;
                case 7: extrato();
                break;
            }
        }
    }

    public void criar(){
        ++id;
        System.out.println("digite seu nome");
        String titular = sc.nextLine();
        double saldo = 0;
        System.out.println("digite sua senha");
        String senha = sc.nextLine();
        Conta c = new Conta(titular,saldo,senha);
        if(lista.containsKey(id)){
            System.out.println("usuario ja existente");
        }else{
            lista.put(id,c);
        System.out.println("conta criada");
        System.out.println("__________________");
        }

    }
    public void depositar(){
        Boolean t1 = true;
        System.out.println("digite o valor que voce deseja depositar");
        double depo = sc.nextDouble();
        System.out.println("digit o id da sua conta");
        int id = sc.nextInt();
        if(!lista.containsKey(id)){
            System.out.println("id invalido");
            t1 = false;
        }if(t1 == true && depo>0){
            Conta c = lista.get(id);
            c.saldo+=depo;
            c.historico("deposito : +"+depo);
            System.out.println("deposito efetuado ");
        }else{
            System.out.println("valor invalido");
            depositar();
        }
    }
    public void sacar(){
        System.out.println("digite seu id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("digite sua senha");
        String senha = sc.nextLine();
        System.out.println("digite o valor do saque");
        double saque = sc.nextDouble();
        if(saque<0){
            saque=0;
            System.out.println("valor invalido");
        }
        sc.nextLine();
        if(lista.containsKey(id)){
            Conta c = lista.get(id);
            String senhatrue = c.getSenha();
            if(senhatrue.equals(senha)){
                double saldo = c.getSaldo();
                if(c.saldo>saque){
                    c.saldo-=saque;
                    c.historico("saque -"+saque);
                }else{
                    System.out.println("saldo insuficiente");
                }
            }
        }else{
            System.out.println("id invalido");
        }
    }
    public void transferir(){
        System.out.println("digite seu id");
        int id1 = sc.nextInt();
        System.out.println("digite o id de quem voce vai enviar");
        int id = sc.nextInt();
        System.out.println("digite o valor da transferencia");
        double valor = sc.nextDouble();
        if(valor<0){
            valor = 0;
            System.out.println("valor invalido");
        }
        if(lista.containsKey(id)&& lista.containsKey(id1)){
            Conta c1 = lista.get(id1);
            Conta c = lista.get(id);
            if(c1.saldo<valor){
                System.out.println("valor insuficiente");
            }else{
                c1.saldo -= valor;
                c1.historico("transferencia -"+valor);
                c.saldo+=valor;
                c.historico("transferencia - "+valor);
            }
        }else{
            System.out.println("ids invalidos");
        }
    }
    public void verConta(){
        System.out.println("digite o id da conta q vc quer ver");
        int id = sc.nextInt();
        if(lista.containsKey(id)){
            System.out.println(id);
            Conta c = lista.get(id);
            System.out.println(c.titular);
            System.out.println(c.saldo);
            System.out.println(c.senha);
        }
    }
    public void listar(){
        for(Conta c:lista.values()){
            c.mostrar();
        }
    }
        public void extrato(){
        System.out.println("digite o id de quem vc quer ver o extrato");
        int id2 = sc.nextInt();
        sc.nextLine();
        if(lista.containsKey(id2)){
            Conta c = lista.get(id2);
            c.extrato();
        }else{
            System.out.println("usuario invalido");
        }
    }
}
public class Main{
    public static void main (String[] args) {
        Banco b = new Banco();
        b.menu();
    }
}