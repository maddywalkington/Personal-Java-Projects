package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Block {
 private int xCoord;
 private int yCoord;
 private int size; // height/width of the square
 private int level; // the root (outer most block) is at level 0
 private int maxDepth;
 private Color color;

 private Block[] children; // {UR, UL, LL, LR}

 public static Random gen = new Random(2);


 public Block() {}

 public Block(int x, int y, int size, int lvl, int  maxD, Color c, Block[] subBlocks) {
  if (maxD < lvl){
   throw new IllegalArgumentException();
  }

  this.xCoord=x;
  this.yCoord=y;
  this.size=size;
  this.level=lvl;
  this.maxDepth = maxD;
  this.color=c;
  this.children = subBlocks;
 }


 public Block(int lvl, int maxDepth) {

  if (maxDepth < lvl){
   throw new IllegalArgumentException();
  }
  this.level = lvl;
  this.maxDepth = maxDepth;
  this.color = null;
  this.children = new Block[0];

  if (lvl < maxDepth) {
   double prob = Math.exp(-0.25 * lvl);
   double randNum = gen.nextDouble();
   if (randNum < prob) {
    // Subdivide the block
    this.children = new Block[4];
    for (int i = 0; i < 4; i++) {
     this.children[i] = new Block(lvl + 1, maxDepth);
    }
   } else {
    // Pick a random color for the block
    int colorIndex = gen.nextInt(GameColors.BLOCK_COLORS.length);
    this.color = GameColors.BLOCK_COLORS[colorIndex];
   }
  } else {
   // Pick a random color for the block
   int colorIndex = gen.nextInt(GameColors.BLOCK_COLORS.length);
   this.color = GameColors.BLOCK_COLORS[colorIndex];
  }
 }

 public void updateSizeAndPosition (int size, int xCoord, int yCoord) {
  if (size < 0 || !isValidSize(size, this.level)) {
   throw new IllegalArgumentException("Invalid size");
  }

  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.children.length != 0) {
   int subSize = size / 2;
   children[1].updateSizeAndPosition(subSize, xCoord, yCoord);
   children[0].updateSizeAndPosition(subSize, xCoord + subSize, yCoord);
   children[2].updateSizeAndPosition(subSize, xCoord, yCoord + subSize);
   children[3].updateSizeAndPosition(subSize, xCoord + subSize, yCoord + subSize);
  }
 }

 private boolean isValidSize(int size, int lvl) {

  if (size == 1 || lvl == maxDepth) {
   return true;
  }
  if (size % 2 == 0) {
   return isValidSize(size / 2, lvl+1);
  }
  else{
   return false;
  }
 }

 public ArrayList<BlockToDraw> getBlocksToDraw() {
  ArrayList<BlockToDraw> blocks = new ArrayList<BlockToDraw>();
  if (this.children.length == 0) {
   blocks.add(new BlockToDraw(color, xCoord, yCoord, size, 0));
   blocks.add(new BlockToDraw(GameColors.FRAME_COLOR, xCoord, yCoord, size, 3));
  } else {
   for (Block b : children) {
    blocks.addAll(b.getBlocksToDraw());
   }
  }
  return blocks;
 }

 /*
  * This method is provided and you should NOT modify it.
  */
 public BlockToDraw getHighlightedFrame() {
  return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
 }



 /*
  * Return the Block within this Block that includes the given location
  * and is at the given level. If the level specified is lower than
  * the lowest block at the specified location, then return the block
  * at the location with the closest level value.
  *
  * The location is specified by its (x, y) coordinates. The lvl indicates
  * the level of the desired Block. Note that if a Block includes the location
  * (x, y), and that Block is subdivided, then one of its sub-Blocks will
  * contain the location (x, y) too. This is why we need lvl to identify
  * which Block should be returned.
  *
  * Input validation:
  * - this.level <= lvl <= maxDepth (if not throw exception)
  * - if (x,y) is not within this Block, return null.
  */


 public Block getSelectedBlock(int clickX, int clickY, int selectedLevel) {
  // Check if the selected level is within range
  if (selectedLevel < level || selectedLevel > maxDepth) {
   throw new IllegalArgumentException("Selected level is out of range.");
  }
  if (selectedLevel == level) {
   return this;
  }
  if (this.children.length == 0) {
   return this;
  } else {
   for (Block subBlock : children) {
    if (clickX >= subBlock.xCoord && clickX < subBlock.xCoord + subBlock.size &&
            clickY >= subBlock.yCoord && clickY < subBlock.yCoord + subBlock.size) {
     return subBlock.getSelectedBlock(clickX, clickY, selectedLevel);
    }
   }
  }
  return null;
 }








 /*
  * Swaps the child Blocks of this Block.
  * If input is 1, swap vertically. If 0, swap horizontally.
  * If this Block has no children, do nothing. The swap
  * should be propagate, effectively implementing a reflection
  * over the x-axis or over the y-axis.
  *
  */


 public void reflect(int direction) {
  if (direction != 0 && direction != 1) {
   throw new IllegalArgumentException("Axis should be 0 or 1");
  }
  if (this.children.length != 0) {
   if (direction == 0) {
    int y1=children[0].yCoord;
    int y2=children[1].yCoord;
    int y3=children[2].yCoord;
    int y4=children[3].yCoord;

    children[0].updateSizeAndPosition(children[0].size, children[0].xCoord, y4);
    children[1].updateSizeAndPosition(children[1].size, children[1].xCoord, y3);
    children[2].updateSizeAndPosition(children[2].size, children[2].xCoord, y2);
    children[3].updateSizeAndPosition(children[3].size, children[3].xCoord, y1);

    Block[] children = {this.children[3], this.children[2], this.children[1], this.children[0]};
    this.children=children;

   }else {
    int x1=children[0].xCoord;
    int x2=children[1].xCoord;
    int x3=children[2].xCoord;
    int x4=children[3].xCoord;

    children[0].updateSizeAndPosition(children[0].size, x2, children[0].yCoord);
    children[1].updateSizeAndPosition(children[1].size, x1, children[1].yCoord);
    children[2].updateSizeAndPosition(children[2].size, x4, children[2].yCoord);
    children[3].updateSizeAndPosition(children[3].size, x3, children[3].yCoord);

    Block[] children = {this.children[1], this.children[0], this.children[3], this.children[2]};
    this.children=children;
   }
   for (Block subBlock : this.children) {
    subBlock.reflect(direction);
   }
   }
  }




  /*
  * Rotate this Block and all its descendants.
  * If the input is 1, rotate clockwise. If 0, rotate
  * counterclockwise. If this Block has no children, do nothing.
  */
 public void rotate(int direction) {
  if (direction != 0 && direction != 1) {
   throw new IllegalArgumentException("Axis should be 0 or 1");
  }
  if (this.children.length != 0) {
   if (direction == 0) {
    int y1=children[0].yCoord;
    int y2=children[1].yCoord;
    int y3=children[2].yCoord;
    int y4=children[3].yCoord;

    int x1=children[0].xCoord;
    int x2=children[1].xCoord;
    int x3=children[2].xCoord;
    int x4=children[3].xCoord;

    children[0].updateSizeAndPosition(children[0].size, x2, y2);
    children[1].updateSizeAndPosition(children[1].size, x3, y3);
    children[2].updateSizeAndPosition(children[2].size, x4, y4);
    children[3].updateSizeAndPosition(children[3].size, x1, y1);

    Block[] children = {this.children[3], this.children[0], this.children[1], this.children[2]};
    this.children=children;

   }else {    int y1=children[0].yCoord;
    int y2=children[1].yCoord;
    int y3=children[2].yCoord;
    int y4=children[3].yCoord;

    int x1=children[0].xCoord;
    int x2=children[1].xCoord;
    int x3=children[2].xCoord;
    int x4=children[3].xCoord;

    children[0].updateSizeAndPosition(children[0].size, x4, y4);
    children[1].updateSizeAndPosition(children[1].size, x1, y1);
    children[2].updateSizeAndPosition(children[2].size, x2, y2);
    children[3].updateSizeAndPosition(children[3].size, x3, y3);

    Block[] children = {this.children[1], this.children[2], this.children[3], this.children[0]};
    this.children=children;
   }
   for (Block subBlock : this.children) {
    subBlock.rotate(direction);
   }
  }
 }



 /*
  * Smash this Block.
  *
  * If this Block can be smashed,
  * randomly generate four new children Blocks for it.
  * (If it already had children Blocks, discard them.)
  * Ensure that the invariants of the Blocks remain satisfied.
  *
  * A Block can be smashed iff it is not the top-level Block
  * and it is not already at the level of the maximum depth.
  *
  * Return True if this Block was smashed and False otherwise.
  *
  */
 public boolean smash() {
  if (this.level == 0 || this.level == maxDepth) {
   return false; // cannot smash top-level or already at max depth
  }

  this.children = new Block[4];

  for (int i = 0; i < 4; i++) {
   this.children[i] = new Block(this.level + 1, maxDepth);
  }
  int subSize = size / 2;
  children[1].updateSizeAndPosition(subSize, xCoord, yCoord);
  children[0].updateSizeAndPosition(subSize, xCoord + subSize, yCoord);
  children[2].updateSizeAndPosition(subSize, xCoord, yCoord + subSize);
  children[3].updateSizeAndPosition(subSize, xCoord + subSize, yCoord + subSize);

  return true;
 }



 /*
  * Return a two-dimensional array representing this Block as rows and columns of unit cells.
  *
  * Return and array arr where, arr[i] represents the unit cells in row i,
  * arr[i][j] is the color of unit cell in row i and column j.
  *
  * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
  */
 public Color[][] flatten() {
  int unit = this.size;
  for (int i = 0; i<maxDepth-this.level; i++){
   unit = unit/2;
  }
   if (this.children.length != 0) {
    Color[][] result = new Color[this.size/unit][this.size/unit];

    for (int i=0; i<(this.size/unit)/2; i++){
     for (int j=0; j<(this.size/unit)/2; j++) {
      Color[][] child = children[1].flatten();
      result[i][j] = child[i][j];
     }
    }

    for (int i=(this.size/unit)/2; i<this.size/unit; i++){
     for (int j=0; j<(this.size/unit)/2; j++) {
      Color[][] child = children[2].flatten();
      result[i][j] = child[i-(this.size/unit)/2][j];
     }
    }

    for (int i=0; i<(this.size/unit)/2; i++){
     for (int j=(this.size/unit)/2; j<this.size/unit; j++) {
      Color[][] child = children[0].flatten();
      result[i][j] = child[i][j-(this.size/unit)/2];
     }
    }

    for (int i=(this.size/unit)/2; i<this.size/unit; i++){
     for (int j=(this.size/unit)/2; j<this.size/unit; j++) {
      Color[][] child = children[3].flatten();
      result[i][j] = child[i-(this.size/unit)/2][j-(this.size/unit)/2];
     }
    }

    return result;
   } else {
    Color[][] result = new Color[this.size/unit][this.size/unit];
    for (int i = 0; i<this.size/unit;i++){
     for (int j = 0; j<this.size/unit;j++){
      result[i][j] = this.color;
     }
    }
    return result;
   }
  }



 // These two get methods have been provided. Do NOT modify them. 
 public int getMaxDepth() {
  return this.maxDepth;
 }

 public int getLevel() {
  return this.level;
 }


 /*
  * The next 5 methods are needed to get a text representation of a block.
  * You can use them for debugging. You can modify these methods if you wish.
  */
 public String toString() {
  return String.format("pos=(%d,%d), size=%d, level=%d"
          , this.xCoord, this.yCoord, this.size, this.level);
 }

 public void printBlock() {
  this.printBlockIndented(0);
 }

 private void printBlockIndented(int indentation) {
  String indent = "";
  for (int i=0; i<indentation; i++) {
   indent += "\t";
  }

  if (this.children.length == 0) {
   // it's a leaf. Print the color!
   String colorInfo = GameColors.colorToString(this.color) + ", ";
   System.out.println(indent + colorInfo + this);
  } else {
   System.out.println(indent + this);
   for (Block b : this.children)
    b.printBlockIndented(indentation + 1);
  }
 }

 private static void coloredPrint(String message, Color color) {
  System.out.print(GameColors.colorToANSIColor(color));
  System.out.print(message);
  System.out.print(GameColors.colorToANSIColor(Color.WHITE));
 }

 public void printColoredBlock(){
  Color[][] colorArray = this.flatten();
  for (Color[] colors : colorArray) {
   for (Color value : colors) {
    String colorName = GameColors.colorToString(value).toUpperCase();
    if(colorName.length() == 0){
     colorName = "\u2588";
    }else{
     colorName = colorName.substring(0, 1);
    }
    coloredPrint(colorName, value);
   }
   System.out.println();
  }
 }

}

