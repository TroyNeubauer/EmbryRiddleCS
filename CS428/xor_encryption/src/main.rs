use std::io;

fn main() {
    println!("Enter your plaintext to encrypt:");
    let mut plaintext = String::new();
    io::stdin()
        .read_line(&mut plaintext)
        .expect("Failed to read input");

    if !plaintext.is_ascii() {
        println!("Plaintext must be ASCII!");
        return;
    }

    println!("Enter your key to do XOR encryption");
    println!("(must be the same length as ciphertext):");

    let mut key = String::new();
    io::stdin()
        .read_line(&mut key)
        .expect("Failed to read input");

    if !key.is_ascii() {
        println!("Encryption key must be ASCII!");
        return;
    }

    if key.len() != plaintext.len() {
        println!(
            "Plaintext and key lengths differ! Plaintext {}, key {}",
            plaintext.len(),
            key.len()
        );
        return;
    }

    let ciphertext: Vec<u8> = plaintext
        .into_bytes()
        .into_iter()
        .zip(key.into_bytes().into_iter())
        .map(|(plain, key)| {
            //Perform XOR encryption
            plain ^ key
        })
        .collect();

    //Key and plaintext are both ascii, so this will be ascii
    let ciphertext = String::from_utf8(ciphertext).unwrap();
    println!("{}", ciphertext);
}
