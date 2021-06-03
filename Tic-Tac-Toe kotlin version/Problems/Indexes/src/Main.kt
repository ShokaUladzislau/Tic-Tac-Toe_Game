fun solution(products: List<String>, product: String) {
    // put your code here
    for (i in 0..products.size - 1) {
        if (product == products[i]) print("$i ")
    }
}
