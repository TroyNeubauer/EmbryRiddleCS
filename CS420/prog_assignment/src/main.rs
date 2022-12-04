use std::{
    sync::atomic::{AtomicIsize, Ordering},
    time::Instant,
};

static AVERAGE: AtomicIsize = AtomicIsize::new(0);
static MIN: AtomicIsize = AtomicIsize::new(0);
static MAX: AtomicIsize = AtomicIsize::new(0);

fn main() {
    let nums: Vec<isize> = std::env::args()
        .skip(1) // skip program name
        .filter_map(|s| s.parse().ok()) // parse each string argement to an isize
        .collect();
    if nums.is_empty() {
        eprintln!("No numbers specified! Please specify numbers after the program name!");
        // exit with failed exit code
        std::process::exit(1);
    }

    eprintln!("Using numbers inputted by the user: {:?}", nums);
    let start = Instant::now();

    std::thread::scope(|s| {
        s.spawn(|| {
            let sum: isize = nums.iter().sum();
            let average = sum / nums.len() as isize;
            AVERAGE.store(average, Ordering::SeqCst)
        });

        s.spawn(|| {
            let min: isize = *nums.iter().min().unwrap();
            MIN.store(min, Ordering::SeqCst)
        });

        s.spawn(|| {
            let max: isize = *nums.iter().max().unwrap();
            MAX.store(max, Ordering::SeqCst)
        });
    });

    let time_taken = start.elapsed();
    eprintln!("Finished calculations in {:?}", time_taken);

    let average = AVERAGE.load(Ordering::SeqCst);
    let min = MIN.load(Ordering::SeqCst);
    let max = MAX.load(Ordering::SeqCst);
    println!("The average value is {}", average);
    println!("The minimum value is {}", min);
    println!("The maximum value is {}", max);
}
