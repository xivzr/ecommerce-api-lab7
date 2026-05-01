/* Problem 1 */

function checkVariable(input) {
    switch (typeof input) {
        case "string":
            return "string";
        case "number":
            return "number";
        case "boolean":
            return "boolean";
        case "bigint":
            return "bigint";
        case "undefined":
            return "undefined";
        case "object":
            return "object";
        default:
            return "object";
    }
}


/* Problem 2: */

function generateIDs(count) {
    let ids = [];

    for (let i = 0; i < count; i++) {

        if (i === 5) {
            continue;
        }

        ids.push(`ID-${i}`);
    }

    return ids;
}

/* Problem 3: */

function calculateTotal(...numbers) {

    for (let n of numbers) {
        if (typeof n !== "number") {
            throw new TypeError("Invalid input: All arguments must be numbers");
        }
    }

    return numbers.reduce((total, num) => total + num, 0);
}

/*Problem 4: */

function getTopScorers(playerList) {

    return playerList
        .filter(player => player.score > 8)
        .map(player => player.name)
        .join(", ");
}

/* Problem 5: */

class Item {

    #discount = 0.1;

    constructor(name, price) {
        this.name = name;
        this.price = price;
    }

    get finalPrice() {
        return this.price - (this.price * this.#discount);
    }

}


/* Problem 6:  */

function safeDivide(a, b) {

    try {

        if (b === 0) {
            throw new Error("Cannot divide by zero");
        }

        return a / b;

    } catch (error) {

        return error.message;

    } finally {

        console.log("Operation attempted");

    }

}

/* TESTS */

console.log("=== Problem 1 Tests ===");

console.log(checkVariable("hello"));
console.log(checkVariable(10));
console.log(checkVariable(true));
console.log(checkVariable(10n));
console.log(checkVariable(undefined));
console.log(checkVariable({}));
console.log(checkVariable(null));



console.log("\n=== Problem 2 Tests ===");
console.log(generateIDs(7));


console.log("\n=== Problem 3 Tests ===");

try {
    console.log(calculateTotal(2,3,5));
} catch (e) {
    console.log(e.message);
}

try {
    console.log(calculateTotal(2,"a",5));
} catch (e) {
    console.log(e.message);
}



console.log("\n=== Problem 4 Tests ===");

let players = [
    {name:"kiya", score:10},
    {name:"Ban", score:5},
    {name:"Chris", score:9},
    {name:"Acky", score:12}
];

console.log(getTopScorers(players));



console.log("\n=== Problem 5 Tests ===");
let item = new Item("Watch",1000);
console.log(item.finalPrice);


console.log("\n=== Problem 6 Tests ===");
console.log(safeDivide(10,2));
console.log(safeDivide(10,0));