# Caesar Cipher Decoder

## Overview
A simple decoder for Caesar cipher texts.  
- Reads ciphertext from `cipher.txt`.  
- Determines shift amount from the user’s CS login using a bitwise XOR key.  
- Applies a right-shift Caesar cipher to decode.  
- Outputs the decoded plaintext.

---

## How It Works
1. Load ciphertext from `cipher.txt`.
2. Prompt for CS login.
3. Compute shift:
   - XOR all characters of login.
   - Take modulo 26 (use 1 if result is 0).
4. Decode each lowercase letter by circular right shift.

---

## Example
```bash
$ ./decode
Your cipher text:
mjqqt btwqi

Your CS login: robinson

Plaintext:
hello world
