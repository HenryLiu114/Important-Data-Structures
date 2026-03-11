public class findNextPrime {
    public static void main(String[] args) {
        System.out.println(getNextPrime(43));
    }

    private static int getNextPrime(int value){
        if(value%2 == 0){
            value++;
        }

        int result = value;
        
        switch(value){
            case 2:
                result = 3;
                break;
            case 3:
                result = 5;
                break;
            case 5:
                result = 7;
                break;
            case 7:
                result = 11;
                break;
            default:
                boolean isPrime = false;
                while(!isPrime){
                    value = value + 2;
                    if(value%3 != 0 && value%5 != 0 && value%7 != 0){
                        result = value;
                        isPrime = true;
                    }
                }
                break;
        }
        return result;
    }
}
