package structure;


//T - Valor Nodo || S - Chave Nodo
public class RBT <T extends Comparable<T>, S extends Comparable<S>> extends Arvore<T, S> {
	private int comp;
	private int rot;
	public int compTotal = 0;
	public int rotTotal = 0;
	private boolean flagBalanced;
	

	public void addNodeRBT(Nodo<T, S> node, boolean showCount)
	{
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		node.setLeftNode(null);
		node.setRightNode(null);
		addNodeRBT(node, getRoot(), showCount, false);
	}
	
	
	private void addNodeRBT(Nodo<T, S> node, Nodo<T, S> root, boolean showCount, boolean flagInserted)
	{
		if (root == null)
		{
			root = getRoot();
		}
		
		if (getRoot() == null)
		{
			setRoot(node);
			flagInserted = true;
		}
		else
		{
			comp++;
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				if (root.getRightNode() != null)
				{
					addNodeRBT(node, root.getRightNode(), showCount, false);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					flagInserted = true;
				}
			}
			else 
			{
				if (root.getLeftNode() != null)
				{
					addNodeRBT(node, root.getLeftNode(), showCount, false);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					flagInserted = true;
				}
			}
			if (flagInserted)
			{
				verifyCases(node, true);
				verifyCases();
				if (showCount)
				{
					// Show the Tree
					super.showRootLevel();
					this.showRotComp();
					this.showTree();
				}
			}
			
		}
	}

	// Deleta Node
	@Override
	public void deleteNode(Nodo<T, S> node)
	{
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0; 
		
		Nodo<T, S> root = node.getRoot();
		
		comp++;

		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			comp++;

			if (root.getLeftNode() != null && root.getLeftNode() == node)
			{
				root.setLeftNode(null);
			}
			else 
			{
				root.setRightNode(null);
			}
		}
		else if (node.getLeftNode() != null ^ node.getRightNode() != null)
		{
			comp++;

			if (node.getLeftNode() != null)
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getLeftNode());
				}
				else 
				{
					root.setRightNode(node.getLeftNode());
				}
			}
			else
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getRightNode());
				}
				else 
				{
					root.setRightNode(node.getRightNode());
				}
			}
		}
		else 
		{
			Nodo<T, S> rightNode = node.getRightNode();
			Nodo<T, S> leftNode = node.getLeftNode();
			
			comp++;
			if (root != null)
			{
				comp++;

				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(null);
				}
				else 
				{
					root.setRightNode(null);
				}
				
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
			else
			{
				setRoot(null);
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
		}
		
		// mostra a arvore
		this.verifyCases();
		super.showRootLevel();
		this.showRotComp();
		this.showTree();
		
	}

	private void doCase1()
	{
		this.getRoot().setBlack(true);
	}
	
	private void doCase2(Nodo<T, S> node)
	{
		Nodo<T, S> pNode = node.getRoot();
		Nodo<T, S> gNode = node.getRoot().getRoot();
		Nodo<T, S> uNode = gNode.getLeftNode() == pNode ? gNode.getRightNode() : gNode.getLeftNode();
		
		// Repaints Parent, GrandP and Uncle
		pNode.setBlack(!pNode.isBlack());
		gNode.setBlack(!gNode.isBlack());
		if (uNode != null) uNode.setBlack(!uNode.isBlack());
	}
	
	private void doCase3(Nodo<T, S> node)
	{
		Nodo<T, S> pNode = node.getRoot();
		if (pNode.getLeftNode() == node)
		{
			super.rightRotation(pNode);
			rot++;
		}
		else
		{
			super.leftRotation(pNode);
			rot++;
		}
	}
	
	private void doCase4(Nodo<T, S> node)
	{
		Nodo<T, S> pNode = node.getRoot();
		Nodo<T, S> gNode = pNode.getRoot();
		
		if (pNode.getLeftNode() == node)
		{
			super.rightRotation(gNode);
			rot++;
		}
		else
		{
			super.leftRotation(gNode);
			rot++;
		}
		
		pNode.setBlack(!pNode.isBlack());
		gNode.setBlack(!gNode.isBlack());
	}
	
	private void verifyCases(Nodo<T, S> node, boolean flag)
	{
		if (this.getRoot() == node && !node.isBlack())
		{
			doCase1();
			flagBalanced = true;
		}
		
		if (node.getRoot() != null && node.getRoot().getRoot() != null && !node.getRoot().isBlack() && !node.isBlack())
		{
			Nodo<T, S> pNode = node.getRoot();
			Nodo<T, S> gNode = pNode.getRoot();
			Nodo<T, S> uNode = gNode.getLeftNode() == null || gNode.getRightNode() == null ? null : gNode.getLeftNode() == pNode ? gNode.getRightNode() : gNode.getLeftNode();
			boolean pLeft = gNode.getLeftNode() == pNode;
			boolean nLeft = pNode.getLeftNode() == node;
			
			if (uNode != null && !uNode.isBlack())
			{
				doCase2(node);
				flagBalanced = true;
			}
			else if (pLeft != nLeft)
			{
				doCase3(node);
				verifyCases(pNode);
				flagBalanced = true;
			}
			else
			{
				doCase4(node);
				flagBalanced = true;
			}
		}
	}
	
	private void verifyCases()
	{
		flagBalanced = false;
		Nodo<T, S> rNode = this.getRoot();
		
		verifyCases(rNode);
		
		if (flagBalanced)
		{
			verifyCases();
		}
	}
	
	private void verifyCases(Nodo<T, S> node)
	{		
		if (node.getLeftNode() != null) verifyCases(node.getLeftNode());
		if (node.getRightNode() != null) verifyCases(node.getRightNode());	
		
		verifyCases(node, true);
	}
	
	
	private void showRotComp()
	{
		System.out.println("Rotações: " + rot);
		System.out.println("Comparações: " + comp);
	}
}
