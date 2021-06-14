using Plots

function groupAverage(filename, groupSize) 

    groupAverages = []

    open(filename) do file

        while ! eof(file)

            group = []
            for i in 1:groupSize
                if ! eof(file)
                    line = parse(UInt128, readline(file))
                    append!(group, line)
                end
            end

            if length(group) == groupSize
                avg = sum(group) / groupSize
                append!(groupAverages, avg)
            end

        end # while
    end # file

    groupAverages

end # func


function main() 

    x = ["100", "1000", "10000", "100000", "1000000", "10000000"] # 100 .. 10m

    #x = x[1:4];

    quickhull  = groupAverage("results/centre_biased_circle/quickhull.txt", 10)     .|> (x -> x / 1000000) #|> (xs -> xs[2:5]) 
    liuchen    = groupAverage("results/centre_biased_circle/liuchen.txt", 10)       .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    alshamrani = groupAverage("results/centre_biased_circle/alshamrani.txt", 10)    .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    torch      = groupAverage("results/centre_biased_circle/torch.txt", 10)         .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    monotone   = groupAverage("results/centre_biased_circle/monotoneChain.txt", 10) .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    chan       = groupAverage("results/centre_biased_circle/chan.txt", 10)          .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    jarvis     = groupAverage("results/centre_biased_circle/jarvis.txt", 10)        .|> (x -> x / 1000000) #|> (xs -> xs[2:5])
    graham     = groupAverage("results/centre_biased_circle/graham.txt", 10)        .|> (x -> x / 1000000) #|> (xs -> xs[2:5])

    #plot( x, quickhull,    label = "Quickhull",      lw = 1, marker = 3, legend = :topleft)
    #plot!(x, liuchen,      label = "Ordered hull",   lw = 1, marker = 3)
    #plot!(x, alshamrani,   label = "Alshamrani",     lw = 1, marker = 3)
    #plot!(x, torch,        label = "Torch",          lw = 1, marker = 3)
    #plot!(x, monotone,     label = "Monotone Chain", lw = 1, marker = 3)
    #plot!(x, chan,         label = "Chan",           lw = 1, marker = 3)
    #plot!(x[1:5], jarvis[1:5],  label = "Gift wrapping",  lw = 1, marker = 3)
    #plot!(x, graham,       label = "Graham scan",  lw = 1, marker = 3)
    #title!("Random uniform square")
    #xlabel!("number of points")
    #ylabel!("execution time (ms)")

    print("Jarvis");          println(transpose(jarvis))
    print("Chan:");           println(transpose(chan))
    print("Graham:");         println(transpose(graham))
    print("Monotone Chain:"); println(transpose(monotone))
    print("Torch");           println(transpose(torch))
    print("Alshamrani:");     println(transpose(alshamrani))
    print("Quickhull:");      println(transpose(quickhull))
    print("LiuChen");         println(transpose(liuchen))


end


