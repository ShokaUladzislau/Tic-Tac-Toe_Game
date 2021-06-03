fun main() {
    // write your code here
    val a = readLine()!!
    var n = 0
    for (ch in a) {
        if (ch == 'c' || ch == 'g' || ch == 'C' || ch == 'G'){
            n++
        }
    }
    println(n.toDouble() / a.length * 100)
}