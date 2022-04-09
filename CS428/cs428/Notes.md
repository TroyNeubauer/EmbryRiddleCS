
# Block Ciphers:

HMAC construct MACs from collision resistant hash functions
Stream vs block ciphers
DES, DES encryption, key managament and decryption

## Stream ciphers
work on one bit at a time. 2 keystreams plaintext and key (usually use xor encryption)

## DES
DES is a block cipher. Created in 1977
56 bit keys, 64 bit block length
16 round Feistel network

Confision: obscures the relationship between the ciphertext and the plaintext. E.g. substitution cipher, OTP

Diffusion: if one bit of the plaintext is changed about half of the bits of ciphertext should change

place in tale is the input bit. Number in table says which bit to set
Input: 0b 0000_0000_0000_0011 0...
14 initial bits of zeroes...
the 15th bit is a one
1 goes to index 12 04

Output:
0b 0001_0000_0001 0...
3 zeroes then a one, then 7 zeroes, then a one, all zeroes
0x101 0...

# DES f function
perform: p box, xor with key, 6 s-boxes, straight p-box, output

input is 37 -> 100101
row = 11 -> 3
col = 0010 -> 2
== 08
1000

# Key schedule

24 bits for each half
LSi and Rsi left rotate i=1,2,9,16 positions

subkeys are bits from the master key

round shift byts are dropped

|   |   |         |         | 1001110 |          |
| 2 | 1 |         |         | 1001010 |          |
|   |   | 1011000 | 1000001 |         | 00011011 |
|   |   | 0100000 | 0010111 |         | 00100101 |
|   |   | 0000111 | 0011101 |         |          |
|   |   | 1011101 | 0010100 |         |          |
|   |   |         |         |         |          |

