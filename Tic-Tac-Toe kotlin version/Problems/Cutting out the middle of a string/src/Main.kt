fun main() {
    // write your code here
    val a = readLine()!!
    if (a.length % 2 == 0) {
        println(a.removeRange(a.length / 2 + a.length % 2 - 1, a.length / 2 + a.length % 2 + 1))
    } else {
        println(a.removeRange(a.length / 2 + a.length % 2 - 1, a.length / 2 + a.length % 2))
    }
}