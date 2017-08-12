import os
import sys

from contextlib import suppress

seeds = set()
for i in os.listdir():
    if i == sys.argv[0]:
        continue
    seeds.add(i.split('_')[0])

sample = 0
mine = 0
mine2 = 0
for i in seeds:
    with suppress(FileNotFoundError):
        with open(i + '_sample.txt', 'r') as f:
            sample_score = float(f.read().split()[6])
        with open(i + '_mine.txt', 'r') as f:
            mine_score = float(f.read().split()[6])
        with open(i + '_mine2.txt', 'r') as f:
            mine2_score = float(f.read().split()[6])
        print(f'{i: <8} ', end='')
        if sample_score < mine_score and sample_score < mine2_score:
            sample += 1
            print('Sample best')
        elif mine_score < sample_score and mine_score < mine2_score:
            mine += 1
            print('Mine best')
        else:
            mine2 += 1
            print('Mine2 best')

print()
print('Num best sample: ' + str(sample))
print('Num best mine:   ' + str(mine))
print('Num best mine2:  ' + str(mine2))
