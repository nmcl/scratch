# The Tree class was interesting, but it did not allow you to
# specify a new tree with a clean user interface. Let the initailizer
# accept a nested structure with hashes and arrays. You should be
# able to specific a tree like this: .....
 
 
# The Tree Class
 
class Tree
 
attr_accessor :childNodes;
attr_accessor :name;
 
 
# I return an initialized Tree instance.
 
def initialize( treeData )
 
# Initialize the node variables. By default, we are going
# to treat this node as the root node (unless the incoming
# tree data only has one top-level key, in which case that
# will be the root node).
 
@name = "root";
@childNodes = [];
 
# Check to see how many top-level keys the tree data has.
# If it only has one, we can use it to define this tree
# node; if it has more than one, will have to treat it as
# the child data of the "root" node.
 
if (treeData.size() == 1)
 
# We only have one top-level key. We can use that to
# define this tree node.
 
@name = treeData.keys()[ 0 ];
 
# Convert the sub-level tree data into the chile nodes
# of this tree node.
 
treeData[ @name ].each{ |key, value| @childNodes.push(Tree.new( { key => value } )); };
  
else
 
# We have more than one top-level key. We need to
# create a root node to house multiple nodes.
 
treeData.each{ |key, value| @childNodes.push(Tree.new( { key => value } )); };
 
end
 
end
 
 
# I visit all decendant nodes in a depth-first traversal.
 
def visitAll( &block )
 
# Visit this node.
 
visit( &block );
 
# Visit all of this node's children.
 
childNodes.each{ |c| c.visitAll( &block ); }
 
end
 
 
# I visit just this node.
 
def visit( &block )
 
block.call( self );
 
end
 
end
 
# ------------------------------------------------------------ #
# ------------------------------------------------------------ #
# ------------------------------------------------------------ #
# ------------------------------------------------------------ #
 
# Create our new Tree.
 
tree = Tree.new({"grandpa" => {"dad" => {"child1" => {},"child2" => {}},"uncle" => {"child3" => {},"child4" => {}}}});

# Visit all nodes in the tree, starting with the root.
 
tree.visitAll{ |node|
 
puts( "Visiting #{node.name}" );
 
};
