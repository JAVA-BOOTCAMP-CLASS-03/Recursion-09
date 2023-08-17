import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionDemo {

    /**
     * Tranforma una lista en un formato String para ser facilmente mostrada en consola
     *
     * @param l  Lista que será tranformada en un String de tipo {elem1, elem2, ...}
     * @return   String que representa a la lista
     */
    public <E> String show(List<E> l) {
        int count = 0;

        StringBuilder strBuffer = new StringBuilder();

        strBuffer.append("{");

        for (E e : l) {
            if (count > 0) {
                strBuffer.append(", ");
            }
            strBuffer.append(e);
            count++;
        }

        strBuffer.append("}");

        return strBuffer.toString();
    }

    public <E> void mostrarAlReves(List<E> l) {
        if (l == null || l.isEmpty() ) {
            return;
        }

        if (l.size() == 1) {
            System.out.println(l.get(0));
        } else {
            mostrarAlReves(l.subList(1, l.size()));
            System.out.println(l.get(0));
        }
    }

    /**
     * Algoritmo de ordenamiento recursivo MergeSort. Un buen ejemplo para ver el comportamiento recursivo.  Le puse
     * bastante comentario para que muestre el camino que sigue.
     *
     * @param l
     * @return
     */
    public <E extends Comparable<E>> List<E> ordenar(List<E> l) {
        System.out.println("ORDENANDO --> " + show(l));
        if ((l != null) && (l.isEmpty() || (l.size() == 1))) {
            System.out.println("ORDEN FINAL --> " + show(l));
            return l;
        }

        System.out.println("SE MEZCLARAN --> " + show(l.subList(0, l.size() / 2)) + " y " + show(l.subList((l.size() / 2), l.size())));

        List<E> lParcial =  mezclar(ordenar(new ArrayList<E>(l.subList(0, l.size() / 2))),
                ordenar(new ArrayList<E>(l.subList((l.size() / 2), l.size()))));

        System.out.println("ORDEN PARCIAL --> " + show(lParcial));

        return lParcial;
    }

    private <E extends Comparable<E>> List<E> mezclar(List<E> l1, List<E> l2) {
        List<E> result = new ArrayList<E>();

        System.out.println("MEZCLANDO --> " + show(l1) + " y " + show(l2));

        if (l1.isEmpty() && l2.isEmpty()) {
            return result;
        } else if (l1.isEmpty()) {
            return l2;
        } else if (l2.isEmpty()) {
            return l1;
        } else {
            E v1 = l1.get(0);
            E v2 = l2.get(0);

            while (!l1.isEmpty() || !l2.isEmpty()) {
                if (v1.compareTo(v2) > 0) {
                    result.add(v2);
                    l2.remove(0);
                    if (!l2.isEmpty()) {
                        v2 = l2.get(0);
                    } else {
                        result.addAll(l1);
                        l1.clear();
                    }
                } else {
                    result.add(v1);
                    l1.remove(0);
                    if (!l1.isEmpty()) {
                        v1 = l1.get(0);
                    } else {
                        result.addAll(l2);
                        l2.clear();
                    }
                }
            }
        }

        System.out.println("MEZCLA OBTENIDA --> " + show(result));


        return result;
    }

    public static void main(String[] args) {
        RecursionDemo rDemo = new RecursionDemo();

        rDemo.mostrarAlReves(Arrays.asList(1, 2, 3, 4, 5));

        List<Integer> lNotOrdered = Arrays.asList(19, 8, 15, 32, 2, 9, 5);

        System.out.println("NO ORDENADA -> " + rDemo.show(lNotOrdered));

        System.out.println("< --------------- INIT SORT ---------------------> ");
        List<Integer> lOrdered = rDemo.ordenar(lNotOrdered);
        System.out.println("< --------------- END SORT ---------------------> ");

        System.out.println("ORDENADA -> " + rDemo.show(lOrdered));
    }
}
